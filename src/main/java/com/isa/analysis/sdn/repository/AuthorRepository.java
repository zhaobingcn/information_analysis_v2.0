package com.isa.analysis.sdn.repository;

import com.isa.analysis.sdn.entity.Author;
import com.isa.analysis.sdn.entity.QueryResult.AuthorAndWorkTogetherTimes;
import com.isa.analysis.sdn.entity.WorkTogether;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by zhzy on 2016/12/30.
 */
@Repository
public interface AuthorRepository extends GraphRepository{

    /**
     * 查询和一个作者紧密合作的作者，按照合作次数排序
     */
    @Query("match (a:Author{name:{name}, institution:{institution}})-[w:work_together]-(b:Author) return b as author, w.weight as times order " +
            "by w.weight desc limit 8")
    List<AuthorAndWorkTogetherTimes> getSortedWorkTogetherAuthorsByAuthor(@Param("name")String name, @Param("institution") String institution);

    /**
     * 查询和一个作者合作过的其他作者
     */
    @Query("match (a:Author{name:{name}, institution:{institution}})-[w:work_together]-(b:Author) return b as authors")
    Collection<Author> getWorkTogetherAuthorsByAuthor(@Param("name")String name, @Param("institution") String institution);

    /**
     * 利用全文索引查找作者信息
     */
    @Query("call userdefined.index.ChineseFullIndexSearch({indexName},{queryContext},{limit}) yield authors")
    List<Author> findByFulltextIndexSearch(@Param("indexName") String indexName, @Param("queryContext") String queryContext, @Param("limit") long limit);

}
