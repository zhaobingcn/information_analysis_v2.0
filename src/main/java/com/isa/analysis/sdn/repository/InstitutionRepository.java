package com.isa.analysis.sdn.repository;

import com.isa.analysis.sdn.entity.Institution;
import com.isa.analysis.sdn.entity.Paper;
import com.isa.analysis.sdn.entity.Patent;
import com.isa.analysis.sdn.entity.QueryResult.InstitutionAndCooperateTimes;
import com.isa.analysis.sdn.entity.QueryResult.KeywordAndInvolveTimes;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhzy on 2016/12/30.
 */
public interface InstitutionRepository extends GraphRepository<Institution> {

    /**
     * 查询和一个作者紧密合作的作者的所属机构，按合作次数排序
     */
    @Query("match (a:Author{name:{name}, institution:{institution}})-[w:work_together]-(b:Author)-[r:works_in]->(i:Institution)" +
            " return i as ins, count(r) as times order by times desc limit 8")
    List<InstitutionAndCooperateTimes> getCooperateInstitutionByAuthor(@Param(value = "name") String name, @Param(value = "institution") String institution);

    @Query("match (a:Author)-[w:work_together]-(b:Author)-[r:works_in]->(i:Institution)" +
            " where id(a)={id} return i as ins, count(r) as times order by times desc limit {limit}")
    List<InstitutionAndCooperateTimes> getCooperateInstitutionByAuthorId(@Param(value = "id") Long id, @Param(value = "limit")long limit);

    /**
     * 查询一个机构的合作机构
     */

    /**
     * 查询一个机构发表的论文
     */
    @Query("match (p:Paper)<-[:publish]-(a:Author)-[:works_in]->(i:Institution)where id(i)={id} return p limit {limit}")
    List<Paper> getPaperOfInstitutionByInstitutionId(@Param(value = "id") Long id, @Param(value = "limit")long limit);

    /**
     * 查询一个机构申请的专利（待做）
     */

    /**
     * 查询一个机构的项目承接（待做）
     */

    /**
     * 查询一个机构的研究方向聚焦
     */

    @Query("match (k:Keyword)<-[t:involve]-(p:Paper)<-[:publish]-(a:Author)-[:works_in]->(i:Institution)where id(i)={id} return k as keyword,count(t) as times limit {limit}")
    List<KeywordAndInvolveTimes> getKeyWordTimesOfInstitutionByInstitutionId(@Param(value = "id") Long id, @Param(value = "limit")long limit);




}
