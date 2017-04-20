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

    Long createNodeOfAuthor(Map<String, String> author);


}
