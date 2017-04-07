package com.isa.analysis.restapi.httprepository.impl;

import com.isa.analysis.restapi.httprepository.RestApiRepository;
import com.isa.analysis.restapi.httprequest.RestQuery;
import org.neo4j.ogm.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hexu on 2017/1/3.
 */
@Repository
public class RestApiRepositoryImpl implements RestApiRepository{
    @Autowired
    private RestQuery restQuery;

    private final String TRANSACTION_URL = "http://neo4j:654321@localhost:7474/db/data/transaction/commit";
    private final String CYPHER_URL = "http://neo4j:654321@localhost:7474/db/data/cypher";
    private final String NODE_URL = "http://neo4j:654321@localhost:7474/db/data/node";

    @Override
    public JSONObject getWorkTogetherPaths(String name, String institution, int depath){

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        parameters.put("institution", institution);
        String[] resultDataContents = new String[] {"graph", "rest"};
        String query = "match path = (a:Author{name:{name}, institution:{institution}}) " +
                "-[:work_together*" + depath + "]-(b:Author) return path";
        JSONObject commitParams = generateStatements(query, parameters, resultDataContents);
        JSONObject graphResult = restQuery.httpPost(TRANSACTION_URL, commitParams);
        return graphResult;
    }

    @Override
    public JSONObject getWorkTogetherPaths(Long id, int depath) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", id);
        String[] resultDataContents = new String[] {"graph", "rest"};
        String query = "match path = (a:Author})" +
                "-[:work_together*" + depath + "]-(b:Author) where id(a)={id} return path";
        JSONObject commitParams = generateStatements(query, parameters, resultDataContents);
        JSONObject graphResult = restQuery.httpPost(TRANSACTION_URL, commitParams);
        return graphResult;
    }


    @Override
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

    @Override
    public Long getDegreeOfNode(Long id, String type) {
        String degreeEndpoint = "/" + id + "/degree" + "/" + type;
        Long nodeDegree = restQuery.httpGetLong(NODE_URL + degreeEndpoint);
        return nodeDegree;
    }

//    public static void main(String[] args){
//        RestApiRepositoryImpl a = new RestApiRepositoryImpl();
//        a.getWorkTogetherPaths("詹毅", "电子科技集团36所", 2);
//    }

}
