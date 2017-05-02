package com.isa.analysis.service.impl;

import com.isa.analysis.restapi.httprepository.RestApiRepository;
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
        return generateGraph(restResult, depath);
    }

    @Override
    public Map<String, Object> generateWorkTogetherGraph(Long id, int depath) {
        JSONObject restResult = restApiRepository.getWorkTogetherPaths(id, depath);
        return generateGraph(restResult, depath);
    }

    @Override
    public Map<String, Object> generateGraph(JSONObject restResult, int depath) {
        /**
         * 两个结果list  node的和relationship的
         */
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> rels = new ArrayList<>();
        /**
         * 用来查询node和relationship是否多次出现过
         */
        Map<Long, Integer> checkNodes = new HashMap<>();
        HashSet<Long> checkRels = new HashSet<>();
        int categoriesCount = depath+1;
        int nodeId = 0;
        try{
            JSONArray paths  = restResult.getJSONArray("results").getJSONObject(0).getJSONArray("data");
            int length = paths.length();
            /**
             * 遍历每一条作者合作路径，既返回graph又返回rest
             */
            for(int pathIndex=0; pathIndex<length; pathIndex++){
                JSONObject graphPath =  paths.getJSONObject(pathIndex).getJSONObject("graph");
                JSONObject restPath = paths.getJSONObject(pathIndex).getJSONArray("rest").getJSONObject(0);

                JSONArray graphPathRelationships = graphPath.getJSONArray("relationships");
                JSONArray graphPathNodes = graphPath.getJSONArray("nodes");

                /**
                 * 利用rest返回的node url构建node id的list,为了在下一步解决是起始节点的几层关系
                 */
                JSONArray restPathNodes = restPath.getJSONArray("nodes");
                List<String> restPathNodesIds = new ArrayList<>();
                for(int restNodeIndex = 0; restNodeIndex < restPathNodes.length(); restNodeIndex ++){
                    String restNodeUrl = restPathNodes.getString(restNodeIndex);
                    int beginIndex = restNodeUrl.lastIndexOf("node/") + 5;
                    String restNodeId = restNodeUrl.substring(beginIndex);
                    restPathNodesIds.add(restNodeId);
                }


                for(int pathNodeIndex = 0; pathNodeIndex < graphPathNodes.length(); pathNodeIndex++){
                    JSONObject anode = graphPathNodes.getJSONObject(pathNodeIndex);
                    if(checkNodes.containsKey(Long.parseLong(anode.getString("id")))){
                        /**
                         * 如果路径中该节点与起始节点直接距离更小，那么用更小的距离代替原距离
                         */
                        if(restPathNodesIds.indexOf(anode.getString("id")) < Long.parseLong(nodes.get(checkNodes.get(Long.parseLong(anode.getString("id")))).get("category").toString())){
                            Map<String, Object> author = nodes.get(checkNodes.get(Long.parseLong(anode.getString("id"))));
                            author.replace("category",  restPathNodesIds.indexOf(anode.getString("id")));
                        }
                    }else{
                        Map<String, Object> author = new HashMap<>();
                        author.put("name", anode.getJSONObject("properties").getString("name"));
                        author.put("institution", anode.getJSONObject("properties").getString("institution"));
                        author.put("value", restApiRepository.getDegreeOfNode(Long.parseLong(anode.getString("id")), "all"));
                        author.put("category", restPathNodesIds.indexOf(anode.getString("id")));
                        checkNodes.put(Long.parseLong(anode.getString("id")), nodeId);
                        nodeId++;
                        nodes.add(author);
                    }
                }

                for(int relationshipIndex = 0; relationshipIndex < graphPathRelationships.length(); relationshipIndex++){
                    JSONObject arelationship = graphPathRelationships.getJSONObject(relationshipIndex);
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
