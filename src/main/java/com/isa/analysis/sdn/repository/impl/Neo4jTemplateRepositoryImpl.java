package com.isa.analysis.sdn.repository.impl;

import com.isa.analysis.sdn.repository.Neo4jTemplateRepository;
import com.isa.analysis.service.impl.MapUtil;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Neo4jSession;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.data.neo4j.template.Neo4jTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Driver;
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

        String createAuthor = "create (a:Author) set a.name={name}, a.institution={institution} return id(a) as id";
        String anotherQuery  ="create (a:Author{name:{name}, institution:{institution}}) return id(a) as id";
        String queryAuthor ="MATCH (n:Author) where n.name={name} and n.institution={institution} return id(n) as id";

        Map<String, String> param = new HashMap<>();
        param.put("name", author.get("name"));
        param.put("institution", author.get("institution"));
        Result result =  neo4jTemplate.query(queryAuthor, param);
        Iterator<Map<String, Object>> mapId = result.iterator();
        if(mapId.hasNext()){
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
            return Long.parseLong(mapId.next().get("id").toString());
        }else{
            System.out.println("---------------------------------------------------");
            Result result1 = neo4jTemplate.query(anotherQuery, author);
            Iterator<Map<String, Object>> mapId1 = result1.iterator();
            return Long.parseLong(mapId1.next().get("id").toString());
        }
    }

    @Override
    public Long createNodeOfPaper(Map<String, Object> paper) {
        String createPaper = "create (p:Paper{title:{title}, quote:{quote}, link:{link}, date:{date}}) return id(p) as id";
        String queryPaper = "match (a:Paper) where a.title={title} return id(a) as id";

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
    public boolean createRelationship(Long id1, Long id2 ) {

        String createRelationship = "match (a) where id(a) = {id1} " +
                "with a " +
                "match (b) where id(b) = {id2} " +
                "with a,b " +
                "create (a)-[w:weight]->(b)";
        String queryRelationship = "match (a)-[]-(b) where id(a) = {id1} " +
                "AND id(b) = {id2} return ";
        return false;
    }


}
