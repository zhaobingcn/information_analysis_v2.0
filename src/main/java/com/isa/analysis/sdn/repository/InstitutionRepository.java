package com.isa.analysis.sdn.repository;

import com.isa.analysis.sdn.entity.Institution;
import com.isa.analysis.sdn.entity.Paper;
import com.isa.analysis.sdn.entity.QueryResult.InstitutionAndCooperateTimes;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhzy on 2016/12/30.
 */
@Repository
public interface InstitutionRepository extends GraphRepository<Institution> {

    /**
     * 通过机构ID查询该机构的名字
     */
    @Query("match (i:Institution) where id(i)={id} return i.name")
    String getInstituionNameByInstitutionId(@Param(value = "id") Long id);

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
     * 查询一个机构的合作机构以及合作次数
     */

    @Query("match (a:Institution)-[t:cooperate]-(b:Institution) where id(a)={id} return b as ins,t.weight as times limit {limit}")
    List<InstitutionAndCooperateTimes> getCooperateInstitutionAndCooperateTimesByInstitutionId(@Param(value = "id") Long id, @Param(value = "limit")long limit);

    /**
     * 查询一个机构的所在地
     */
    @Query("match (a:Institution) where id(a)={id} return a.location")
    String getInstitutionLocationByInstitutionId(@Param(value = "id") Long id);
    /**
     * 查询一个机构发表的论文
     */
    @Query("match (p:Paper)<-[:publish]-(a:Author)-[:works_in]->(i:Institution)where id(i)={id} return  DISTINCT p ORDER BY p.date limit {limit}")
    List<Paper> getPaperOfInstitutionByInstitutionId(@Param(value = "id") Long id, @Param(value = "limit")long limit);

    /**
     * 查询一个机构申请的专利（待做）
     */

    /**
     * 查询一个机构的项目承接（待做）
     */


    /**
     * 查询一个机构的竞争机构（根据这个机构下的论文的关键字查到其他的论文根据其他机构含有这些论文的数量来决定竞争程度）
     */
    @Query("match (i:Institution)-[:works_in]-(a:Author)-[:publish]-(p:Paper) where id(i)={id} with DISTINCT  p"+
            " match (p)-[t:involve]-(k:Keyword) with k,count(t) as times where times > 1 match (p:Paper)-[:involve]-(k) with DISTINCT p"+
            " match (i1:Institution) match (i:Institution)-[:works_in]-(:Author)-[:publish]-(p:Paper) where id(i1)={id} "+
            "AND NOT (i1)-[:cooperate]-(i)  with i,count(p) as times ORDER BY times desc return i limit {limit}")
    List<Institution> getCompeteInstitutionByByInstitutionId(@Param(value = "id") Long id, @Param(value = "limit")long limit);

    /**
     * 查询一个机构的潜在合作机构（根据这个机构下的作者，有共同合作作者的作者所在的机构）
     */
    @Query("match (i:Institution)-[:works_in]-(a1:Author) where id(i)={id} with DISTINCT a1 match (a1)-[:work_together]-(a2:Author)-[:work_together]-(a3:Author)"+
            " where NOT (a1)-[:work_together]-(a3) with DISTINCT a3 match (i:Institution) where id(i)={id} AND NOT (i)-[:works_in]-(a3) with DISTINCT a3"+
            " match (i1:Institution)-[:works_in]-(a3:Author) match (i:Institution) where id(i)={id} AND NOT (i)-[:cooperate]-(i1) return i1,count(a3) as times ORDER BY times desc limit{limit}")
    List<Institution> getPotentialCooperateInstitutionByInstitutionId(@Param(value = "id") Long id, @Param(value = "limit")long limit);





}
