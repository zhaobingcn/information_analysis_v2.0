package com.isa.analysis.service.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * Created by zhzy on 17-4-11.
 */
@Component
public class Scheduler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron="0 0 22 * * ?") //每分钟执行一次
    public void statusCheck() {
        logger.info("每分钟执行一次。开始……");
        //statusTask.healthCheck();
        logger.info("开始执行了");
        logger.info("每分钟执行一次。结束。");
    }

//    @Scheduled(fixedRate=20000)
//    public void testTasks() {
//        logger.info("每20秒执行一次。开始……");
//        //statusTask.healthCheck();
//        logger.info("每20秒执行一次。结束。");
//    }
}