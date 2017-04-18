package com.isa.analysis.service.runtime.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * Created by zhzy on 17-4-11.
 */
@Component
public class Scheduler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron="0 0 22 * * ?") //每晚22点开始执行
    public void checkFromMongoDB() {


    }

    public boolean createNodeOfAuthor(){
        return true;
    }

    public boolean createNodeOfPaper(){
        return true;
    }

    public boolean createNodeOfInstitution(){
        return true;
    }

    public boolean createNodeOfKeyword(){
        return true;
    }

    public boolean createNodeOfJournal(){
        return true;
    }
}