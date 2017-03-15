package com.isa.analysis.sdn.repository;

import com.isa.analysis.sdn.entity.Keyword;
import com.isa.analysis.sdn.entity.QueryResult.KeywordAndInvolveTimes;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhzy on 2016/12/30.
 */
@Repository
public interface KeywordRepository extends GraphRepository<Keyword> {

    @Query( "match (a:Author{name:{name}, institution:{institution}})" +
            "-[:publish]->(p:Paper)-[i:involve]->(k:Keyword) return k as keyword, count(i) as times")
    List<KeywordAndInvolveTimes> getKeywordsByAuthor(@Param(value = "name")String name,
                                                     @Param(value = "institution")String institution);

    //TODO
   "match (a:Author)-[:publish]->(p:Paper)-[i:involve]->(k:Keyword) where id(a)=65 with k.name as keywords,"+
    "count(i) as times, sum(p.quote) as cited order by times desc limit 3 return keywords, times, cited"
}
