package com.isa.analysis.sdn.repository;

import com.isa.analysis.sdn.entity.WorkTogether;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhzy on 2017/1/7.
 */
@Repository
public interface WorkTogetherRepository extends GraphRepository<WorkTogether> {

    @Query("match (a:Author{name:{name}, institution:{institution}})-[w:work_together]-(b) return w")
    List<WorkTogether> findWorkGetherByAuthor(@Param(value = "name")String name, @Param(value = "institution")String institution);


}
