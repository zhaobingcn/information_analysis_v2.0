package com.isa.analysis.service.runtime.impl;

import com.isa.analysis.sdn.repository.Neo4jTemplateRepository;
import com.isa.analysis.service.runtime.MongoDBDao;
import com.mongodb.DBObject;
import com.mongodb.util.Util;
import org.json.JSONObject;
import org.neo4j.ogm.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by zhzy on 17-4-11.
 */
@Service
public class Scheduler {

    @Autowired
    ConvertToNode convertToNode;

    @Autowired
    Neo4jTemplateRepository neo4jTemplateRepository;

    @Autowired
    MongoDBDao mongoDBDao;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Scheduled(cron="0 18 11 * * ?") //每晚22点开始执行
//    @Scheduled(fixedRate=6000)
    public void checkFromMongoDB() {

/*
{ "_id" : ObjectId("58ed9b581d41c8682a33c232"), "institutions" : { "华东交通大学" : "南昌" }, "title" : "从认知语言学看二外词汇习得模式和教学", "url" : "http://s.wanfangdata.com.cn/Paper.aspx?q= 题名:深度学习", "include" : null, "spidertime" : "2017-04-12 11:13:28", "quote" : 0, "abstract" : { "Chinese" : "本文从认知心理学的角度出发,着重讨论了二外学习者在词汇习得过程中的认知特点和习得过程,希望能够给词汇教学提供教学参考.首先综述了先前研究提出的两个学习者词汇认知模式:层次网络模式、激活扩散模式,探讨了二语词汇深度习得模式,并由此构建了词汇教学的对策和方法,为英语教师提供了一定参考." }, "authors" : { "聂晶" : { "location" : "南昌", "institution" : "华东交通大学" } }, "date" : { "year" : "2009", "period" : "20" }, "journal" : "考试周刊", "keywords" : [ "认知语言学", "词汇习得模式", "二语词汇教学" ], "link" : "http://d.wanfangdata.com.cn/Periodical/kszk200920087" }
 */
        Date date = new Date();
        //获取当前时间-24小时的时间
        long times = date.getTime()-1000*60*60*24*13;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(times);
        System.out.println(dateString);
        //设置查找条件
        HashMap<Object, Object> timeConfig = new HashMap<>();
        System.out.println(dateString);
        timeConfig.put("$gte", dateString);

        ArrayList<DBObject> c = mongoDBDao.find("wanFang", "paperinfo", new String[]{"spidertime"}, new Object[]{timeConfig}, -1);

        if(c.isEmpty()){
            logger.info("MongoDB中没有更新，不需要导入数据");
        }
        else{
            for(DBObject line: c){
                System.out.println(line);
                String lines = line.toString();
                JSONObject objectLine = new JSONObject(lines);

                //创建论文的节点，返回的id用于创建关系
                Map<String, Object> paper = convertToNode.getPaper(objectLine);
                //删除可能重复的论文
                if(paper.get("date").equals("000000")){
                    continue;
                }

                Long paperId = neo4jTemplateRepository.createNodeOfPaper(paper);

                //创建机构节点，返回id的列表用于创建关系
                Map<String, Long> institutionId = new HashMap<>();
                List<Map<String, String>> institutions = convertToNode.getInsittution(objectLine);
                for(Map<String, String> institution:institutions){
                    institutionId.put(institution.get("name"), neo4jTemplateRepository.createNodeOfInstitution(institution));
                }

                //创建机构之间的合作关系
                List<Long> listInstitutionsId = new ArrayList<>();
                for(Map.Entry<String, Long> institution: institutionId.entrySet()){
                    listInstitutionsId.add(institution.getValue());
                }
                for(int i=0; i<listInstitutionsId.size()-1; i++){
                    for(int j=i+1; j<listInstitutionsId.size(); j++){
                        neo4jTemplateRepository.createRelationship(listInstitutionsId.get(i), listInstitutionsId.get(j),
                                "cooperate", 1);
                    }
                }


                //创建作者节点，返回id的列表用于创建关系
                List<Long> authorsId = new ArrayList<>();
                List<Map<String, String>> authors = convertToNode.getAuthors(objectLine);
                for (Map<String, String> author : authors) {
                    Long authorId = neo4jTemplateRepository.createNodeOfAuthor(author);
                    authorsId.add(authorId);
                    //创建作者机构之间的关系
                    neo4jTemplateRepository.createRelationship(authorId, institutionId.get(author.get("institution")), "works_in", 1);
                }

                //创建作者之间的关系
                for(int i=0; i<authorsId.size()-1; i++){
                    for(int j=i+1; j<authorsId.size(); j++){
                        neo4jTemplateRepository.createRelationship(authorsId.get(i), authorsId.get(j),
                                "work_together", 1);
                    }
                }


                //创建杂志节点，返回的id用于创建关系
                Map<String, String> journal = convertToNode.getJournal(objectLine);
                Long journalId = neo4jTemplateRepository.createNodeOfJournal(journal);
                neo4jTemplateRepository.createRelationship(paperId, journalId, "included_in", 1);


                //创建关键词节点，返回的关系列表用于创建关系
                List<Long> keywordsId = new ArrayList<>();
                List<String> keywords = convertToNode.getKeyWords(objectLine);
                for(String keyword:keywords){
                    keywordsId.add(neo4jTemplateRepository.createNodeOfKeyword(keyword));
                }

                //创建关键词之间的关系
                for(int i=0; i<keywordsId.size()-1; i++){
                    for(int j = i+1; j<keywordsId.size(); j++){
                        neo4jTemplateRepository.createRelationship(keywordsId.get(i), keywordsId.get(j),
                                "similar", 1);
                    }
                }

                //创建论文到关键词关系
                for(int i=0; i<keywordsId.size(); i++){
                    neo4jTemplateRepository.createRelationship(paperId, keywordsId.get(i), "involve", 1);
                }

                //创建作者到论文的关系
                for(int i=0; i<authorsId.size(); i++){
                    neo4jTemplateRepository.createRelationship(authorsId.get(i), paperId, "publish", (int)(1.0/authorsId.size()*100));
                }
            }
        }
    }

}