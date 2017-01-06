package com.isa.analysis.sdn.repository;

import com.isa.analysis.sdn.entity.Author;
import com.isa.analysis.sdn.entity.WorkTogether;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhzy on 2016/12/30.
 */
@Repository
public interface AuthorRepository extends GraphRepository<Author>{

    /**
     * 查询和一个作者紧密合作的作者，按照合作次数排序
     */
    @Query("match (a:Author{name:{name}, institution:{institution}})-[w:work_together]-(b:Author) return b, w order " +
            "by w.weight desc limit 8")
    List<Map<Author, WorkTogether>> getWorkTogetherAuthorsByAuthor(@Param("name")String name, @Param("institution") String institution);

}
