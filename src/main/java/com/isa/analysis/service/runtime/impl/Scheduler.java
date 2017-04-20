package com.isa.analysis.service.runtime.impl;

import com.mongodb.DBObject;
import com.mongodb.util.Util;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.processing.SupportedSourceVersion;
import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by zhzy on 17-4-11.
 */
@Component
public class Scheduler {

    @Autowired
    ConvertToNode convertToNode;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Scheduled(cron="0 0 22 * * ?") //每晚22点开始执行
    public void checkFromMongoDB() {

/*
{ "_id" : ObjectId("58ed9b581d41c8682a33c232"), "institutions" : { "华东交通大学" : "南昌" }, "title" : "从认知语言学看二外词汇习得模式和教学", "url" : "http://s.wanfangdata.com.cn/Paper.aspx?q= 题名:深度学习", "include" : null, "spidertime" : "2017-04-12 11:13:28", "quote" : 0, "abstract" : { "Chinese" : "本文从认知心理学的角度出发,着重讨论了二外学习者在词汇习得过程中的认知特点和习得过程,希望能够给词汇教学提供教学参考.首先综述了先前研究提出的两个学习者词汇认知模式:层次网络模式、激活扩散模式,探讨了二语词汇深度习得模式,并由此构建了词汇教学的对策和方法,为英语教师提供了一定参考." }, "authors" : { "聂晶" : { "location" : "南昌", "institution" : "华东交通大学" } }, "date" : { "year" : "2009", "period" : "20" }, "journal" : "考试周刊", "keywords" : [ "认知语言学", "词汇习得模式", "二语词汇教学" ], "link" : "http://d.wanfangdata.com.cn/Periodical/kszk200920087" }
 */
        String createAuthorCypher = "create (a:Author{name:{name}, institution:{institution}) return id(a)";
        String createPaperCypher = "create (a:Paper{title:{title}, link:{link}, }) return id(a)";
        String createJournalCypher = "create (a:Journal{name:{name}}) return id(a)";
        String createInstitutionCypher = "create (a:Institution{name:{name}, location:{location}}) return id(a)";
        String createKeywordCypher = "create (a:Keyword{name:{name}}) return id(a)";

        String createRelationShip = "create (a)-[r]->(b) where id(a) = {ida} AND id(b) = {idb}";

        String queryAuthor ="match (a:Author{name:{name}, institution:{institution}) return id(a)";
        String queryPaper = "match (p:Paper{title:{title}}) return id(p)";
        String queryJournal = "match (j:Journal{name:{name}}) return id(j)";
        String queryInstitution = "match (i:Institution{name:{name}}) return id(i)";
        String queryKeyword = "match (k:Keyword{name:{name}}) return id(k)";

        String queryRelationship = "match (a)-[r]->(b) where id(a) = {iad} AND id(b) = {idb} return id(r)";

        MongoDBDaoImpl a = MongoDBDaoImpl.getMongoDBDaoImplInstance();
        Date date = new Date();
        //获取当前时间-24小时的时间
        long times = date.getTime()-1000*60*60*24;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(times);
        //设置查找条件
        HashMap<Object, Object> timeConfig = new HashMap<>();
        System.out.println(dateString);
        timeConfig.put("$lte", dateString);

        ArrayList<DBObject> c = a.find("wanFang", "paperinfo", new String[]{"spidertime"}, new Object[]{timeConfig}, -1);

        if(c.isEmpty()){
            logger.info("MongoDB中没有更新，不需要导入数据");
        }
        else{
            for(DBObject line: c){
                String lines = line.toString();
                JSONObject objectLine = new JSONObject(lines);
                List<Map<String, String>> authors = convertToNode.getAuthors(objectLine);

//                for (Map<String, String> map : authors) {
//                    System.out.println(map.get("name"));
//                }

                List<Map<String, String>> institutions = convertToNode.getInsittution(objectLine);
//                for (Map<String, String> map : institutions) {
//                    System.out.println(map.get("name"));
//                }

                Map<String, Object> paper = convertToNode.getPaper(objectLine);
//                System.out.println(paper.get("title"));

                Map<String, String> journal = convertToNode.getJournal(objectLine);
//                System.out.println(journal.get("name"));
//
                List<String> include = convertToNode.getInclude(objectLine);
//                for(String inc:include){
//                    System.out.println(inc);
//                }
//
                List<String> keywords = convertToNode.getKeyWords(objectLine);
//                for(String key:keywords){
//                    System.out.println(key);
//                }
//                System.out.println(objectLine.toString());


            }
        }
//        if(!c.isEmpty()){
//            c.forEach(x -> System.out.println(new JSONObject(x)));
//        }
    }



    public Long createNodeOfAuthor(){
        String createAuthorCypher = "create (a:Author{name:{name}, institution:{institution}) return id(a)";

        return 0l;
    }

    public Long createNodeOfPaper(){
        return 0l;
    }

    public Long createNodeOfInstitution(){
        return 0l;
    }

    public Long createNodeOfKeyword(){
        return 0l;
    }

    public Long createNodeOfJournal(){
        return 0l;
    }

    public boolean createRelationship(){
        return true;
    }

    public static void main(String[] args) throws Exception{
//        UtilRead in = new UtilRead();
//        BufferedReader bufferedReader = in.getBufferedReaderForJson("./Logs/new.dat");
//        String line = "";
//        while (true) {
//            line = bufferedReader.readLine();
//            if (line == null || line.trim().equals("")) {
//                break;
//            }
//            System.out.println(line);
//            ConvertToNode convert = new ConvertToNode();
//            JSONObject objectLine = new JSONObject(line);
//            List<Map<String, String>> authors = convert.getAuthors(objectLine);
//
//            for (Map<String, String> map : authors) {
//                System.out.println(map.get("name"));
//            }
//        }
        Scheduler a = new Scheduler();
        a.checkFromMongoDB();
    }
}