package com.isa.analysis.sdn.repository;

import com.isa.analysis.sdn.entity.Keyword;
import com.isa.analysis.sdn.entity.QueryResult.KeywordAndInvolveTimes;
import com.isa.analysis.sdn.entity.Similar;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhzy on 2016/12/30.
 */
@Repository
public interface KeywordRepository extends GraphRepository<Keyword> {

    @Query( "match (a:Author{name:{name}, institution:{institution}})" +
            "-[:publish]->(p:Paper)-[i:involve]->(k:Keyword) return k as keyword, count(i) as times")
    List<KeywordAndInvolveTimes> getKeywordsByAuthor(@Param(value = "name")String name,
                                                     @Param(value = "institution")String institution);

    @Query( "match (a:Author)-[:publish]->(p:Paper)-[i:involve]->(k:Keyword) where id(a)={id} return k as keyword, count(i) as times")
    List<KeywordAndInvolveTimes> getKeywordsByAuthor(@Param(value = "id")Long id);

    @Query("match (a:Author)-[:publish]->(p:Paper)-[i:involve]->(k:Keyword) where id(a)={id} return k as keyword, " +
            "count(i) as times order by times desc limit {limit}")
    List<KeywordAndInvolveTimes> getSortedKeywordsByAuthorId(@Param(value = "id")Long id, @Param(value = "limit")int limit);

    @Query("match (p:Paper)<-[:publish]-(a:Author)-[:works_in]->(i:Institution) where id(i)={id} with DISTINCT p match (p)-[t:involve]-(k:Keyword) return k as keyword,count(t) as times ORDER BY times desc limit {limit}")
    List<KeywordAndInvolveTimes> getKeyWordTimesOfInstitutionByInstitutionId(@Param(value = "id") Long id, @Param(value = "limit")long limit);

    /**
     * 前20个社区
     * @return
     */
    @Query("match (k:Keyword) return k.partition as partition, count(k) as score order by score desc limit 20")
    List<Map<Long, Integer>> getKeywordsPartition();

    /**
     * 每个社区中limit个
     * @param partition
     * @param limit
     * @return
     */
    @Query("match (p:Paper)-[i:involve]->(k:Keyword) where k.partition={partition} return k as keyword, " +
            "count(i) as times order by times desc limit {limit}")
    List<KeywordAndInvolveTimes> getKeywordsByPartition(@Param(value = "partition")Long partition, @Param(value = "limit") Integer limit);

    /**
     * 查询一个关键词相似度最高的5个关键词，根据名字查询
     */
    @Query("MATCH (k:Keyword{name:{name}})-[s:similar]-(k2:Keyword) RETURN k2.name as name, s.weight AS score ORDER BY score DESC LIMIT 5")
    List<Map<String, Object>> getSimilarKeywords(@Param(value = "name")String name);

    @Query("match (k:Keyword) where id(k)={id} with k match p = (k)-[:similar*1..2]-(:Keyword) return nodes(p)")
    List<List<Keyword>> getRelatedKeywordsWithDepath(@Param(value = "id")Long id);

}
