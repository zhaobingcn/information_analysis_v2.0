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

    /**
     * 查询一个作者合作的作者，使用姓名和机构名作为参数
     * @param name
     * @param institution
     * @return
     */
    @Query("match (a:Author{name:{name}, institution:{institution}})-[w:work_together]-(b) return w")
    List<WorkTogether> findWorkGetherByAuthor(@Param(value = "name")String name, @Param(value = "institution")String institution);

    /**
     * 查询一个作者合作的作者，使用id作为参数
     * @param id
     * @return
     */
    @Query("match (a:Author)-[w:work_together]-(b) where id(a)={id} return w")
    List<WorkTogether> findWorkGetherById(@Param(value = "id")Long id);


}
