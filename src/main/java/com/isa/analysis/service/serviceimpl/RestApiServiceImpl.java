package com.isa.analysis.service.serviceimpl;

import com.isa.analysis.restapi.httprepository.RestApiRepository;
import com.isa.analysis.restapi.httprequest.RestQuery;
import com.isa.analysis.service.MapUtil;
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

    @Autowired
    private MapUtil mapUtil;

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
                JSONArray pathRelationships = path.getJSONArray("relationships");
                JSONArray pathNodes = path.getJSONArray("nodes");
                for(int pathNodeIndex = 0; pathNodeIndex < pathNodes.length(); pathNodeIndex++){
                    JSONObject anode = pathNodes.getJSONObject(pathNodeIndex);
//                    if(checkNodes.containsKey(Long.parseLong(anode.getString("id")))){
//                        /**
//                         * 如果路径中该节点与起始节点直接距离更小，那么用更小的距离代替原距离
//                         */
//                        if(pathNodes.length() - pathNodeIndex - 1 < Long.parseLong(nodes.get(checkNodes.get(Long.parseLong(anode.getString("id")))).get("category").toString())){
//                            Map<String, Object> author = nodes.get(checkNodes.get(Long.parseLong(anode.getString("id"))));
//                            author.replace("category",  pathNodes.length() - pathNodeIndex - 1);
//                        }
//                    }else{
                        Map<String, Object> author = new HashMap<>();
                        author.put("name", anode.getJSONObject("properties").getString("name"));
                        author.put("institution", anode.getJSONObject("properties").getString("institution"));
                        System.out.println(anode.getJSONObject("properties").getString("institution"));
                        author.put("value", restApiRepository.getDegreeOfNode(Long.parseLong(anode.getString("id")), "all"));
//                        author.put("category", pathNodes.length() - pathNodeIndex - 1);
                        checkNodes.put(Long.parseLong(anode.getString("id")), nodeId);
                        nodeId++;
                        nodes.add(author);
//                    }
                }

                for(int relationshipIndex = 0; relationshipIndex < pathRelationships.length(); relationshipIndex++){
                    JSONObject arelationship = pathRelationships.getJSONObject(relationshipIndex);
                    if(checkRels.contains(Long.parseLong(arelationship.getString("id")))){
                        continue;
                    }else{
                        checkRels.add(Long.parseLong(arelationship.getString("id")));
                        int startNodeId, endNodeId;
                        startNodeId = checkNodes.get(Long.parseLong(arelationship.getString("startNode")));
                        endNodeId = checkNodes.get(Long.parseLong(arelationship.getString("endNode")));

                        HashMap<String, Object> rel = new HashMap<>();
                        rel.put("source", startNodeId);
                        rel.put("target", endNodeId);
                        rel.put("value", arelationship.getJSONObject("properties").getString("weight"));
                        rel.put("type", arelationship.getString("type"));
                        rels.add(rel);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        List<Map<String, Object>> categories = new ArrayList<>();
        categories.add(mapUtil.map("name", "专家", "keyword", null, "base", "Author"));
        for(int categoryIndex=1; categoryIndex<categoriesCount; categoryIndex++){
            categories.add(mapUtil.map("name", categoryIndex + "层合作关系", "keyword", null, "base", "Author"));
        }
        return mapUtil.map("type", "force", "categories", categories, "nodes", nodes, "links", rels);
    }
}
