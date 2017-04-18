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

/*
{ "_id" : ObjectId("58ed9b581d41c8682a33c232"), "institutions" : { "华东交通大学" : "南昌" }, "title" : "从认知语言学看二外词汇习得模式和教学", "url" : "http://s.wanfangdata.com.cn/Paper.aspx?q= 题名:深度学习", "include" : null, "spidertime" : "2017-04-12 11:13:28", "quote" : 0, "abstract" : { "Chinese" : "本文从认知心理学的角度出发,着重讨论了二外学习者在词汇习得过程中的认知特点和习得过程,希望能够给词汇教学提供教学参考.首先综述了先前研究提出的两个学习者词汇认知模式:层次网络模式、激活扩散模式,探讨了二语词汇深度习得模式,并由此构建了词汇教学的对策和方法,为英语教师提供了一定参考." }, "authors" : { "聂晶" : { "location" : "南昌", "institution" : "华东交通大学" } }, "date" : { "year" : "2009", "period" : "20" }, "journal" : "考试周刊", "keywords" : [ "认知语言学", "词汇习得模式", "二语词汇教学" ], "link" : "http://d.wanfangdata.com.cn/Periodical/kszk200920087" }

 */
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