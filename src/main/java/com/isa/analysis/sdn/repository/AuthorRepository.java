package com.isa.analysis.sdn.repository;

import com.isa.analysis.sdn.entity.Author;
import com.isa.analysis.sdn.entity.QueryResult.AuthorAndWorkTogetherTimes;
import com.isa.analysis.sdn.entity.WorkTogether;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by zhzy on 2016/12/30.
 */
@Repository
public interface AuthorRepository extends GraphRepository<Author>{

    /**
     * 查询和一个作者紧密合作的作者，按照合作次数排序，使用姓名和机构作为参数
     */
    @Query("match (a:Author{name:{name}, institution:{institution}})-[w:work_together]-(b:Author) return b as author, w.weight as times order " +
            "by w.weight desc limit 8")
    List<AuthorAndWorkTogetherTimes> getSortedWorkTogetherAuthorsByAuthor(@Param("name")String name, @Param("institution") String institution);

    @Query("match (a:Author) where id(a)={id} return a")
    Author findById(@Param("id") Long id);

    /**
     * 查询和一个作者紧密合作的作者，按照合作次序排序，使用id作为参数
     */
    @Query("match (a:Author)-[w:work_together]-(b:Author) where id(a) = {id} return b as author, w.weight as times order by w.weight desc limit 8")
    List<AuthorAndWorkTogetherTimes> getSortedWorkTogetherAuthorsById(@Param("id")Long id);

    /**
     * 查询和一个作者合作过的其他作者,使用姓名和机构作为参数
     */
    @Query("match (a:Author{name:{name}, institution:{institution}})-[w:work_together]-(b:Author) return b as authors")
    Collection<Author> getWorkTogetherAuthorsByAuthor(@Param("name")String name, @Param("institution") String institution);

    /**
     * 查询和一个作者合作过的其他作者,使id参数
     */
    @Query("match (a:Author)-[w:work_together]-(b:Author) where id(a) = {id} return b as authors")
    Collection<Author> getWorkTogetherAuthorsByAuthor(@Param("id")Long id);

    /**
     * 利用全文索引查找作者信息
     */
    @Query("call userdefined.index.ChineseFullIndexSearch({indexName},{queryContext},{limit})")
    List<Author> findByFulltextIndexSearch(@Param("indexName") String indexName, @Param("queryContext") String queryContext, @Param("limit") long limit);

    /**
     * 查找一个作者的文献引用量，使用姓名，机构名作为参数
     * @param name
     * @param institution
     * @return
     */
    @Query("match (a:Author{name:{name}, institution:{institution}})-[:publish]->(p:Paper) return sum(p.quote) as quoteCount")
    int getPapersQuoteCount(@Param("name")String name, @Param("institution")String institution);

    /**
     * 查找一个作者的文献引用量，使用id作为参数
     * @param id
     * @return
     */
    @Query("match (a:Author)-[:publish]->(p:Paper) where id(a) = {id} return sum(p.quote) as quoteCount")
    int getPapersQuoteCountById(@Param("id")Long id);

    /**
     * 查找一个关键词下面的top作者
     */
    @Query("match (a:Author)-[:publish]->(p:Paper)-[:involve]->(k:Keyword) WHERE id(k)={id} WITH a,collect(p) AS papers " +
            "RETURN a ORDER BY (reduce(sum=0, p IN papers|sum+p.quote + 10)) DESC LIMIT {limit}")
    List<Author> getTopAuthorsByKeywordsId(@Param("id")Long id, @Param("limit")Integer limit);

    /**
     * 专家推荐页面的查找，几个关键词查找出最相关的作者,按照论文成果排
     */

    @Query("match (a:Author)-[:publish]->(p:Paper)-[:involve]->(k:Keyword) " +
            "WHERE k.name= {kname1} OR k.name= {kname2} OR k.name= {kname3} OR k.name= {kname4} " +
            "AND a.name<>{aname1} AND a.name<>{aname2} with a,collect(p) AS papers " +
            "RETURN a ORDER BY (reduce(sum=0, p IN papers|sum+p.quote + 10)) DESC LIMIT 10")
    List<Author> getTopAuthorsByKeywords(@Param("kname1")String kname1,
                                         @Param("kname2")String kname2,
                                         @Param("kname3")String kname3,
                                         @Param("kname4")String kname4,
                                         @Param("aname1")String aname1,
                                         @Param("aname2")String aname2
                                         );

    /**
     * 专家推荐页面的查找，几个关键词查找出最相关的作者,按照PageRank排
     */




}
