package com.isa.analysis.sdn.repository;

import com.isa.analysis.sdn.entity.Institution;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhzy on 2016/12/30.
 */
@Repository
public interface InstitutionRepository extends GraphRepository<Institution> {

    /**
     * 查询和一个作者紧密合作的作者的所属机构，按合作次数排序
     */
    @Query("match (a:Author{name:{name}, institution:{institution}})-[w:work_together]-(b:Author)-[r:works_in]->(i:Institution)" +
            " return i, count(r) as times order by times desc limit 8")
    List<Map<Institution, Long>> getCooperateInstitutionByAuthor(String name, String institution);
}
