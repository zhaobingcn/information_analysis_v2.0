package com.isa.analysis.service;

import com.isa.analysis.restapi.httprequest.RestQuery;
import com.isa.analysis.sdn.entity.Author;
import org.neo4j.ogm.json.JSONArray;
import org.neo4j.ogm.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hexu on 2016/12/30.
 */
@Service
public class TestRestApi {

    @Autowired
    private RestQuery restQuery;

    final String TRANSACTION_URL = "http://neo4j:654321@localhost:7474/db/data/transaction/commit";
    final String CYPHER_URL = "http://neo4j:654321@localhost:7474/db/data/cypher";

    public void test(){
        try{
//            JSONObject queryparams = new JSONObject();
//            queryparams.put("query", "match path = (a:Author{name:{name}, institution:{institution}}) " +
//                    "-[:work_together*" + 3 + "]-(b:Author) return path");
//            JSONObject params = new JSONObject();
//            params.put("name", "詹毅");
//            params.put("institution", "电子科技集团36所");
//            queryparams.put("params", params);
//            JSONObject jsonObject = restQuery.httpPost(CYPHER_URL, queryparams);
//            Map<String, Object> map = jsonObject.toMap();
//            System.out.println(jsonObject.toString());

            JSONObject queryparams1 = new JSONObject();
            queryparams1.put("statement", "match path = (a:Author)-[:work_together*" + 3 + "]-(b:Author) return path limit 10");
            queryparams1.put("resultDataContents", new String[] {"graph"});

            System.out.println(queryparams1.toString());

            JSONArray querystatements = new JSONArray();
            querystatements.put(queryparams1);

            System.out.println(querystatements.toString());

            JSONObject finaldata = new JSONObject();
            finaldata.put("statements", querystatements);

            System.out.println(finaldata.toString());

            JSONObject jsonObject = restQuery.httpPost(CYPHER_URL, finaldata);
            System.out.println(jsonObject.toString());


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public JSONObject generateGraph(String name, String institution){


        return null;
    }



//    public static void main(String[] args){
//        TestRestApi t = new TestRestApi();
//        t.test();
//    }

}
