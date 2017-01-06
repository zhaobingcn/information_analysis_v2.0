package com.isa.analysis.sdn.repository;

import com.isa.analysis.sdn.entity.Paper;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by zhzy on 2016/12/30.
 */
@Repository
public interface PaperRepository extends GraphRepository<Paper> {

    @Query("match (a:Author{name:{name}, institution:{institution}})-[:publish]->(p:Paper) return p")
    Collection<Paper> findByAuthor(@Param("name")String name, @Param("institution")String institution);


}
