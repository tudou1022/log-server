package com.boge.logserver.service.impl;

import com.boge.logserver.constant.LogLevel;
import com.boge.logserver.dao.LogInfoTestMapper;
import com.boge.logserver.domain.LogInfoTest;
import com.boge.logserver.service.KafkaConsumerService;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class KafkaConsumerServiceImpl  implements KafkaConsumerService {

    @Value("${zookeeper.server}")
    private String zookeeperServer;
    @Value("${zookeeper.port}")
    private String zookeeperPort;
    @Autowired
    private LogInfoTestMapper logInfoTestMapper;

    @Override
    public void listenLogInfo(String topic,String groupId) {
        ConsumerConnector consumer = createConsumer(groupId);
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, 1); // 一次从主题中获取一个数据
        Map<String, List<KafkaStream<byte[], byte[]>>> messageStreams = consumer.createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> stream = messageStreams.get(topic).get(0);// 获取每次接收到的这个数据
        ConsumerIterator<byte[], byte[]> iterator = stream.iterator();
        while (iterator.hasNext()) {
            String message = new String(iterator.next().message());
            System.out.println("接收到: " + message);
            if(!StringUtils.isEmpty(message)){
                storageLogInfo(message);
            }
        }
    }

    private void storageLogInfo(String message){
        //格式：= "2019/05/29 14:44:57-ERROR-[com.boge.logsend.Demo.Log4jProducer]-[Log4jProducer:43]-错误信息/ by zero";
        String[] split = message.split("-");
        String time = split[0];
        String level = split[1];
        String sclass = split[2];
        String method = split[3];
        String messmage = split[4];
        LogInfoTest logInfoTest = new LogInfoTest();
        logInfoTest.setLevel(LogLevel.valueOf(level).getValue());
        logInfoTest.setClasspath(sclass);
        logInfoTest.setErrorMethod(method);
        logInfoTest.setErrorInfo(messmage);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Date parse = simpleDateFormat.parse(time);
            logInfoTest.setCreatetime(parse);
            //判断时间是否为1小时之前，是则不插入
//            if(new Date().getTime() - parse.getTime() > 1000*60*60){
//                return ;
//            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        logInfoTestMapper.insertSelective(logInfoTest);
    }

    private ConsumerConnector createConsumer(String groupId) {
        Properties properties = new Properties();
        properties.put("zookeeper.connect",zookeeperServer+":"+zookeeperPort);// 声明zk
        properties.put("group.id", groupId);// 必须要使用别的组名称，如果生产者和消费者都在同一组，则不能访问同一组内的topic数据
        return Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));
    }
}
