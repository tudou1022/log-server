package com.boge.logserver.controller;

import com.boge.logserver.service.KafkaConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @ClassName: AnnotationsController
 * @Description: TODO
 * @Author: zhangwb
 * @Date: 2019/5/29 11:10
 **/
@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class AnnotationsController {

    @Autowired
    KafkaConsumerService service;
    //3.添加定时任务 例如：5秒
    @Scheduled(cron = "0/5 * * * * ?")
    private void configureTasks() {
        service.listenLogInfo("test","group1");
    }
}
