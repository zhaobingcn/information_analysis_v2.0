package com.isa.analysis.sdn.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhzy on 2016/12/31.
 */
public interface Neo4jTemplateRepository {

    /**
     * 查询全局范围内的实体数量
     * @param entityClass
     * @return
     */
    Long getCountOfEntities(Class entityClass);

    /**
     * 查询全局范围内主要的机构，关键词，作者，杂志
     * @param entityName
     * @return
     */
    Map<String, Long> getInfluentialEntities(String entityName, int limit);


    /**
     * 查询一个作者的主要研究方向，研究方向论文，引用数等信息
     */

    List<Map<String, Object>> getAuthorsComparsionInformations(Long id);

    /**
     * 建立作者节点
     * @param author
     * @return
     */
    Long createNodeOfAuthor(Map<String, String> author);


    /**
     * 建立论文节点
     * @param paper
     * @return
     */
    Long createNodeOfPaper(Map<String, Object> paper);

    /**
     * 建立机构节点
     * @param institution
     * @return
     */
    Long createNodeOfInstitution(Map<String, String> institution);

    /**
     * 建立关键词节点
     * @param name
     * @return
     */
    Long createNodeOfKeyword(String name);

    /**
     * 建立所属杂志节点
     * @param journal
     * @return
     */
    Long createNodeOfJournal(Map<String, String> journal);

    /**
     * 建立关系 如果关系已存在则修改weight属性值
     * @param id1
     * @param id2
     * @return
     */
    Long createRelationship(Long id1, Long id2, String relationshipType, int weight);

    /**
     *
     */
    Long getKeywordRelatedPapersCount(Integer year, Long keywordId);

    Long getKeywordRelatedInstitutionsCount(Integer year, Long keywordId);

    Long getKeywordRelatedAuthorsCount(Integer year, Long keywordId);
    /**
     * 运行社区发现算法
     */
    void community();

    /**
     * 查找关键节点
     */
    void betweenness();

    /**
     * 发现意见领袖
     */
    void centrileness();

    /**
     * 运行pagerank
     */
    void pagerank();
}
