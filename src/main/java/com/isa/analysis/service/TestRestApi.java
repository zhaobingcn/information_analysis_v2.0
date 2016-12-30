package com.isa.analysis.service;

import com.isa.analysis.restapi.RestQuery;
import org.neo4j.ogm.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by hexu on 2016/12/30.
 */
@Service
public class TestRestApi {

    @Autowired
    private RestQuery restQuery;

    String CYPHER_URL = "http://neo4j:654321@localhost:7474/db/data/cypher/";

    public void test(){
        try{
            JSONObject queryparams = new JSONObject();
            queryparams.put("query", "match (m:Movie{title:{title}}) return m");
            JSONObject params = new JSONObject();
            params.put("title", "Unforgiven");
            queryparams.put("params", params);
            JSONObject jsonObject = restQuery.httpPost(CYPHER_URL, queryparams);
//            Map<String, Object> map = jsonObject.toMap();
            System.out.println(jsonObject.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    public static void main(String[] args){
//        TestRestApi t = new TestRestApi();
//        t.test();
//    }

}
