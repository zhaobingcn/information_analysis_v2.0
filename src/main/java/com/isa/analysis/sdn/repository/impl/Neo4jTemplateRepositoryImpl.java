package com.isa.analysis.sdn.repository.impl;

import com.isa.analysis.sdn.repository.Neo4jTemplateRepository;
import com.isa.analysis.service.impl.MapUtil;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.result.ResultRestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.data.neo4j.template.Neo4jTemplate;
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
    @Transactional
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
    public List<Map<String, Object>> getAuthorsComparsionInformations(Long id) {

        String cypher = "match (a:Author)-[:publish]->(p:Paper)-[i:involve]->(k:Keyword) where id(a)={id} with k.name as keyword, " +
                " count(i) as involveTimes, count(p) as papersCount, collect(p) as papers order by involveTimes desc limit 3" +
                " return keyword, involveTimes, papersCount, reduce(sum=0, p in papers| sum+p.quote) as papersQuote";

        Result result = neo4jTemplate.query(cypher, mapUtil.map("id", id));
        Iterator<Map<String, Object>> resultMap = result.iterator();
        List<Map<String, Object>> comparsionInformations = new ArrayList<>();
        while(resultMap.hasNext()){
            Map<String, Object> row = resultMap.next();
            comparsionInformations.add(row);
        }
        return comparsionInformations;
    }

    @Override
    @Transactional
    public Long createNodeOfAuthor(Map<String, String> author){

        String anotherQuery  ="create (a:Author{name:{name}, institution:{institution}}) return id(a) as id";
        String queryAuthor ="MATCH (n:Author) where n.name={name} and n.institution={institution} return id(n) as id";

        Map<String, String> param = new HashMap<>();
        param.put("name", author.get("name"));
        param.put("institution", author.get("institution"));
        Result result =  neo4jTemplate.query(queryAuthor, param);
        Iterator<Map<String, Object>> mapId = result.iterator();
        if(mapId.hasNext()){
            return -1l;
        }else{
            Result result1 = neo4jTemplate.query(anotherQuery, author);
            Iterator<Map<String, Object>> mapId1 = result1.iterator();
            return Long.parseLong(mapId1.next().get("id").toString());
        }
    }

    @Override
    @Transactional
    public Long createNodeOfPaper(Map<String, Object> paper) {
        String createPaper = "create (p:Paper{title:{title}, quote:{quote}, link:{link}, date:{date}}) return id(p) as id";
        String queryPaper = "match (a:Paper) where a.link={link} return id(a) as id";

        Map<String, String> param = new HashMap<>();
        param.put("link", paper.get("link").toString());
        Result result = neo4jTemplate.query(queryPaper, param);
        Iterator<Map<String, Object>> mapId = result.iterator();
        if(mapId.hasNext()){
            return Long.parseLong(mapId.next().get("id").toString());
        }else{
            Result result1 = neo4jTemplate.query(createPaper, paper);
            Iterator<Map<String, Object>> mapId1 = result1.iterator();
            return Long.parseLong(mapId1.next().get("id").toString());
        }
    }

    @Override
    @Transactional
    public Long createNodeOfInstitution(Map<String, String> institution) {
        String createInstitution = "create (i:Institution{name:{name}, location:{location}}) return id(i) as id";
        String queryInstitution = "match (i:Institution{name:{name}}) return id(i) as id";

        Map<String, String> param = new HashMap<>();
        param.put("name", institution.get("name").toString());
        Result result = neo4jTemplate.query(queryInstitution, param);
        Iterator<Map<String, Object>> mapId = result.iterator();
        if(mapId.hasNext()){
            return Long.parseLong(mapId.next().get("id").toString());
        }else{
            Result result1 = neo4jTemplate.query(createInstitution, institution);
            Iterator<Map<String, Object>> mapId1 = result1.iterator();
            return Long.parseLong(mapId1.next().get("id").toString());
        }
    }

    @Override
    @Transactional
    public Long createNodeOfKeyword(String name) {
        String createKeyword = "create (k:Keyword{name:{name}}) return id(k) as id";
        String queryKeyword = "match (k:Keyword{name:{name}}) return id(k) as id";

        Map<String, String> param = new HashMap<>();
        param.put("name", name);
        Result result = neo4jTemplate.query(queryKeyword, param);
        Iterator<Map<String, Object>> mapId = result.iterator();
        if(mapId.hasNext()){
            return Long.parseLong(mapId.next().get("id").toString());
        }else{
            Result result1 = neo4jTemplate.query(createKeyword, param);
            Iterator<Map<String, Object>> mapId1 = result1.iterator();
            return Long.parseLong(mapId1.next().get("id").toString());
        }
    }

    @Override
    @Transactional
    public Long createNodeOfJournal(Map<String, String> journal) {
        String createJournal = "create (j:Journal{name:{name}}) return id(j) as id";
        String queryJournal = "match (j:Journal{name:{name}}) return id(j) as id";

        Result result = neo4jTemplate.query(queryJournal, journal);
        Iterator<Map<String, Object>> mapId = result.iterator();
        if(mapId.hasNext()){
            return Long.parseLong(mapId.next().get("id").toString());
        }else{
            Result result1 = neo4jTemplate.query(createJournal, journal);
            Iterator<Map<String, Object>> mapId1 = result1.iterator();
            return Long.parseLong(mapId1.next().get("id").toString());
        }
    }

    @Override
    @Transactional
    public Long createRelationship(Long id1, Long id2 , String relationshipType, int weight) {

        String createRelationship = "match (a) where id(a) = {id1} " +
                "with a " +
                "match (b) where id(b) = {id2} " +
                "with a,b " +
                "create (a)-[rel:" + relationshipType + "{weight:{weight}}]->(b) return id(rel) as id";
        String queryRelationship = "match (a)-[rel:" + relationshipType + "]-(b) where id(a) = {id1} " +
                "AND id(b) = {id2} return id(rel) as id";

        String updateRelationship =  "match (a)-[rel:" + relationshipType + "]-(b) where id(a) = {id1} " +
                "AND id(b) = {id2} set rel.weight = rel.weight + " + weight + " return id(rel) as id";

        Map<String, Object> param = new HashMap<>();
        param.put("id1", id1);
        param.put("id2", id2);
        Result result = neo4jTemplate.query(queryRelationship, param);
        Iterator<Map<String, Object>> mapId = result.iterator();
        if(mapId.hasNext()){
            if(relationshipType.equals("cooperate") || relationshipType.equals("work_together")
                    || relationshipType.equals("similar")) {
                param.put("weight", weight);
                Result result1 = neo4jTemplate.query(updateRelationship, param);
                Iterator<Map<String, Object>> newMapId = result1.iterator();
                return Long.parseLong(newMapId.next().get("id").toString());
            }else{
                return Long.parseLong(mapId.next().get("id").toString());
            }
        }else{
            param.put("weight", weight);
            Result result1 = neo4jTemplate.query(createRelationship, param);
            Iterator<Map<String, Object>> mapId1 = result1.iterator();
            return Long.parseLong(mapId1.next().get("id").toString());
        }
    }

    @Override
    @Transactional
    public Long getKeywordRelatedPapersCount(Integer year, Long keywordId) {
        String cypher = "match (k:Keyword)<-[:involve]-(p:Paper) where id(k) = {keywordId} AND toInt(p.date)>=" +year+ "01 AND toInt(p.date)<" + year+1 + "01 return count(distinct(p)) as count";
        Result result = neo4jTemplate.query(cypher, mapUtil.map("keywordId", keywordId));
        Long count = 0l;
        Iterator<Map<String, Object>> countMap = result.iterator();
        while (countMap.hasNext()){
            count = Long.parseLong(countMap.next().get("count").toString());
        }
        return count;
    }

    @Override
    @Transactional
    public Long getKeywordRelatedInstitutionsCount(Integer year, Long keywordId) {
        String cypher = "match (k:Keyword)<-[:involve]-(p:Paper) where id(k) = {keywordId} AND toInt(p.date)>=" +year+ "01 AND toInt(p.date)<" + year+1 + "01 with p " +
                "match (p)<-[:publish]-(a:Author)-[:works_in]->(i:Institution) return count(distinct(i)) as count";
        Result result = neo4jTemplate.query(cypher, mapUtil.map("keywordId", keywordId));
        Long count = 0l;
        Iterator<Map<String, Object>> countMap = result.iterator();
        while (countMap.hasNext()){
            count = Long.parseLong(countMap.next().get("count").toString());
        }
        return count;
    }

    @Override
    @Transactional
    public Long getKeywordRelatedAuthorsCount(Integer year, Long keywordId) {
        String cypher = "match (k:Keyword)<-[:involve]-(p:Paper) where id(k) = {keywordId} AND toInt(p.date)>=" +year+ "01 AND toInt(p.date)<" + year+1 + "01 with p " +
                "match (p)<-[:publish]-(a:Author) return count (distinct(a)) as count";
        Result result = neo4jTemplate.query(cypher, mapUtil.map("keywordId", keywordId));
        Long count = 0l;
        Iterator<Map<String, Object>> countMap = result.iterator();
        while (countMap.hasNext()){
            count = Long.parseLong(countMap.next().get("count").toString());
        }
        return count;
    }

    @Override
    public void community() {

    }
}
