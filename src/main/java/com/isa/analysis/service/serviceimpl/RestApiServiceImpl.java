package com.isa.analysis.service.serviceimpl;

import com.isa.analysis.restapi.httprepository.RestApiRepository;
import com.isa.analysis.restapi.httprequest.RestQuery;
import com.isa.analysis.service.RestApiService;
import org.neo4j.ogm.json.JSONArray;
import org.neo4j.ogm.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by hexu on 2017/1/3.
 */
@Service
public class RestApiServiceImpl implements RestApiService {

    @Autowired
    private RestApiRepository restApiRepository;

    @Override
    public Map<String, Object> generateWorkTogetherGraph(String name, String institution, int depath) {
        JSONObject restResult = restApiRepository.getWorkTogetherPaths(name, institution, depath);
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> rels = new ArrayList<>();
        Map<Long, Integer> checkNodes = new HashMap<>();
        HashSet<Long> checkRels = new HashSet<>();
        int categoriesCount = depath+1;
        int nodeId = 0;

        try{
            JSONArray paths  = restResult.getJSONArray("results").getJSONObject(0).getJSONArray("data");
            int length = paths.length();
            /**
             * 遍历每一条作者合作路径
             */
            for(int pathIndex=0; pathIndex<length; pathIndex++){
                JSONObject path =  paths.getJSONObject(pathIndex).getJSONObject("graph");
                List<Map<String, Object>> pathRelationships = (List<Map<String, Object>>)path.getJSONArray("relationships");
                List<Map<String, Object>> pathNodes = (List<Map<String, Object>>)path.getJSONArray("nodes");
                int pathNodeIndex = 0;
                for(Map<String, Object> anode:pathNodes){
                    if(checkNodes.containsKey(Long.parseLong(anode.get("id").toString()))){
                        /**
                         * 如果路径中该节点与起始节点直接距离更小，那么用更小的距离代替原距离
                         */
                        if(pathNodeIndex < Long.parseLong(nodes.get(checkNodes.get(Long.parseLong(anode.get("id").toString()))).get("category").toString())){
                            Map<String, Object> author = nodes.get(checkNodes.get(Long.parseLong(anode.get("id").toString())));
                            author.replace("category",  pathNodeIndex);
                        }
                        pathNodeIndex ++;
                        continue;
                    }else{
                        Map<String, Object> author = new HashMap<>();
                        author.put("name", anode.get("name"));
                        author.put("institution", anode.get("institution"));
                        author.put("value", restApiRepository.getDegreeOfNode(Long.parseLong(anode.get("id").toString()), "cooperate"));
                        author.put("category", pathNodeIndex);
                        pathNodeIndex++;
                        checkNodes.put(Long.parseLong(anode.get("id").toString()), nodeId);
                        nodeId++;
                        nodes.add(author);
                    }
                }

                for(Map<String, Object> arelationship:pathRelationships){
                    if(checkRels.contains(Long.parseLong(arelationship.get("id").toString()))){
                        continue;
                    }else{
                        checkRels.add(Long.parseLong(arelationship.get("id").toString()));
                        int startNodeId, endNodeId;


                    }

                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
