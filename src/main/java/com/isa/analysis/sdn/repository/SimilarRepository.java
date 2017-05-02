package com.isa.analysis.sdn.repository;

import com.isa.analysis.sdn.entity.Similar;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhzy on 17-5-2.
 */
@Repository
public interface SimilarRepository extends GraphRepository<Similar> {

    @Query("match (k:Keyword) where id(k)={id} with k match p = (k)-[:similar*1..2]-(:Keyword) return relationships(p)")
    List<List<Map<String, Object>>> getRelationshipsWithDeapth(@Param(value = "id") Long id);
}
