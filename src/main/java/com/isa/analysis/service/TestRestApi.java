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
    RestQuery restQuery;

    public void test(){
        String url = "http://localhost:7474/db/data/cypher";
        try{
            JSONObject queryparams = new JSONObject();
            queryparams.put("query", "match (m:Movie{title:{title}}) return n");
            JSONObject params = new JSONObject();
            params.put("title", "Unforgiven");
            queryparams.put("params", params);
            JSONObject jsonObject = restQuery.httpPost(url, queryparams);
            System.out.println(jsonObject.toString());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
