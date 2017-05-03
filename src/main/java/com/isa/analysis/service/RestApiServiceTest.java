package com.isa.analysis.service;

import com.isa.analysis.restapi.httprequest.RestQuery;
import com.isa.analysis.sdn.entity.Author;
import org.apache.commons.collections.map.HashedMap;
import org.neo4j.ogm.json.JSONArray;
import org.neo4j.ogm.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hexu on 2016/12/30.
 */
@Service
public class RestApiServiceTest {

//    @Autowired
    private RestQuery restQuery = new RestQuery();

    final String TRANSACTION_URL = "http://neo4j:654321@localhost:7474/db/data/transaction/commit";
    final String CYPHER_URL = "http://neo4j:654321@localhost:7474/db/data/cypher";

    public void test(){
        try{
            JSONObject queryparams = new JSONObject();
            queryparams.put("query", "match path = (a:Author{name:{name}, institution:{institution}}) " +
                    "-[:work_together*" + 3 + "]-(b:Author) return path");
            JSONObject params = new JSONObject();
            params.put("name", "詹毅");
            params.put("institution", "电子科技集团36所");
            queryparams.put("params", params);
            JSONObject jsonObject = restQuery.httpPost(CYPHER_URL, queryparams);
            Map<String, Object> map = jsonObject.toMap();
            System.out.println(jsonObject.toString());

//            JSONObject queryparams1 = new JSONObject();
//            queryparams1.put("statement", "match path = (a:Author)-[:work_together*" + 3 + "]-(b:Author) return path limit 10");
//            queryparams1.put("resultDataContents", new String[] {"graph"});
//
//            System.out.println(queryparams1.toString());
//
//            JSONArray querystatements = new JSONArray();
//            querystatements.put(queryparams1);
//
//            System.out.println(querystatements.toString());
//
//            JSONObject finaldata = new JSONObject();
//            finaldata.put("statements", querystatements);
//
//            System.out.println(finaldata.toString());
//
//            JSONObject jsonObject = restQuery.httpPost(CYPHER_URL, finaldata);
//            System.out.println(jsonObject.toString());


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void generateWorkTogetherGraph(Long id, int depath){

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        String[] resultDataContents = new String[] {"graph", "rest"};
        String query = "match path = (a:Author)" +
                "-[:work_together*" + depath + "]-(b:Author) where id(a)={id} return path";
        JSONObject commitParams = generateStatements(query, parameters, resultDataContents);
        JSONObject graphResult = restQuery.httpPost(TRANSACTION_URL, commitParams);
        Map<String, Object> map = graphResult.toMap();
        System.out.println(map.toString());
    }



//    public static void main(String[] args){
//        RestApiServiceTest t = new RestApiServiceTest();
//        t.generateWorkTogetherGraph(63l,3);
//    }

    public JSONObject generateStatements(String query, Map<String, Object> parameters, String[] resultDataContents){
        Map<String, Object> commitParams = new HashMap<>();

        List<Map<String, Object>> statements = new ArrayList<>();

        Map<String, Object> statement = new HashMap<>();

        statement.put("statement", query);
        statement.put("parameters", parameters);
        statement.put("resultDataContents", resultDataContents);

        statements.add(statement);

        commitParams.put("statements", statements);
        return new JSONObject(commitParams);
    }

}
