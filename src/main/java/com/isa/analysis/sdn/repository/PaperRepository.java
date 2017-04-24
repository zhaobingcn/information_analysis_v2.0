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

    @Query("match (a:Author)-[:publish]-(p:Paper) where id(a) = {authorId} return p")
    Collection<Paper> findByAuthor(@Param("authorId")Long authorId);

    /**
     * 得到一个作者的论文的数
     * @param name
     * @param institution
     * @return
     */
    @Query("match (a:Author{name:{name}, institution:{institution}})-[:publish]->(p:Paper) return count(p) as pcount")
    int getPapersCountByAuthor(@Param("name")String name, @Param("institution")String institution);

    @Query("match (a:Author)-[:publish]->(p:Paper) where id(a)={authorId} return count(p) as pcount")
    int getPapersCountByAuthorId(@Param("authorId")Long authorId);


    /**
     * 查询一个作者发的论文，有分页
     * @param name
     * @param institution
     * @param skip
     * @param limit
     * @return
     */
    @Query("match (a:Author{name:{name}, institution:{institution}})-[:publish]-(p:Paper)" +
            "return p order by p.date desc skip {skip} limit {limit}")
    List<Paper> getPapersByAuthorWithPages(@Param(value = "name") String name, @Param(value = "institution")
            String institution, @Param(value = "skip") int skip, @Param(value = "limit") int limit);

    @Query("match (a:Author)-[:publish]-(p:Paper) where id(a) = {authorId}" +
            "return p order by p.date desc skip {skip} limit {limit}")
    List<Paper> getPapersByAuthorIdWithPages(@Param(value = "authorId")Long authorId, @Param(value = "skip") int skip, @Param(value = "limit") int limit);

    /**
     * 查询一个关键词相关度最高的论文
     */
    @Query("MATCH (k:Keyword{name:{name}})<-[i:involve]-(p:Paper) RETURN p LIMIT 5")
    List<Paper> getPapersByKeywords(@Param(value = "name")String name);
}
