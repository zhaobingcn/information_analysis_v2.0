package com.isa.analysis.sdn.repository;

import com.isa.analysis.sdn.entity.Author;
import com.isa.analysis.sdn.entity.Paper;
import com.isa.analysis.sdn.entity.WorkTogether;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by zhzy on 2016/12/30.
 */
//@Repository
@RepositoryRestResource(collectionResourceRel = "papers", path = "papers")
public interface PaperRepository extends GraphRepository<Paper> {

    /**
     * 查找一个作者写过的论文
     * @param name
     * @param institution
     * @return
     */
    @Query("match (a:Author{name:{name}, institution:{institution}})-[:publish]->(p:Paper) return p")
    Collection<Paper> findByAuthor(@Param("name")String name, @Param("institution")String institution);

    /**
     * 得到一个作者的论文的数
     * @param name
     * @param institution
     * @return
     */
    @Query("match (a:Author{name:{name}, institution:{institution}})-[:publish]->(p:Paper) return count(p) as pcount")
    Long getPapersCountByAuthor(@Param("name")String name, @Param("institution")String institution);

}
