package com.isa.analysis.sdn.repository.impl;

import com.isa.analysis.sdn.repository.Neo4jTemplateRepository;
import com.isa.analysis.service.MapUtil;
import org.neo4j.ogm.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by zhzy on 2016/12/31.
 */
@Repository
public class Neo4jTemplateRepositoryImpl implements Neo4jTemplateRepository {

    @Autowired
    private Neo4jOperations neo4jTemplate;

    @Autowired
    private MapUtil mapUtil;

    @Override
    public Long getCountOfEntities(Class entityClass) {
        Long entityCount = neo4jTemplate.count(entityClass);
        return entityCount;
    }

    @Override
    @Transactional
    public Map<String, Long> getInfluentialEntities(String entityName, int limit) {
        String cypher = "";
        switch (entityName){
            case "Institution":
                cypher = "match (i:Institution)<-[:works_in]-(a:Author)-[pu:publish]->(p:Paper) " +
                        "return i.name as name, sum(pu.weight) as score order by score desc limit {limit}";
                break;
            case "Keyword":
                cypher = "match (k:Keyword)<-[i:involve]-(p:Paper) " +
                        "return k.name as name, count(i) as score order by score desc limit {limit}";
                break;
            case "Author":
                cypher = "match (a:Author)-[pu:publish]->(p:Paper)" +
                        " return a.name as name, sum(pu.weight) as score order by score desc limit {limit}";
                break;
            case "Journal":
                cypher = "match (j:Journal)<-[in:included_in]-(p:Paper) " +
                        "return j.name as name,count(in) as score order by score desc limit {limit}";
        }
        Result result = neo4jTemplate.query(cypher, mapUtil.map("limit", limit));
        Iterator<Map<String, Object>> resultMap = result.iterator();
        Map<String, Long> influentialEntity = new LinkedHashMap<>();
        while(resultMap.hasNext()){
            Map<String, Object> row = resultMap.next();
            influentialEntity.put(row.get("name").toString(), Long.parseLong(row.get("score").toString()));
        }
        return influentialEntity;
    }

    @Override
    @Transactional
    public Map<String, Object> getKeywordsByAuthor(String name, String institution) {
        String query = "match (a:Author{name:{name}, institution:{institution}})" +
                "-[:publish]->(p:Paper)-[i:involve]->(k:Keyword) return k.name as kname, count(i) as times";
        Map<String, Object> params =  new HashMap<>();
        params.put("name", name);
        params.put("institution", institution);
        Result result = neo4jTemplate.query(query, params);
        Iterator<Map<String, Object>> resultKeywords = result.iterator();
        Map<String, Object> keywords = new HashMap<>();
        while (resultKeywords.hasNext()){
            Map<String, Object> row = resultKeywords.next();
            keywords.put(row.get("kname").toString(), row.get("times"));
        }
        return keywords;
    }

}
