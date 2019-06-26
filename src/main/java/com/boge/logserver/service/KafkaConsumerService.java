package com.boge.logserver.service;


/**
 * log 日志收集
 */
public interface KafkaConsumerService {

    public void listenLogInfo(String topic,String groupId);

}
