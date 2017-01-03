package com.isa.analysis.service;

import java.util.Map;

/**
 * Created by hexu on 2017/1/3.
 */
public interface IndexPageService {

    /**
     * 有影响力的关键词，专家，机构，limit为显示个数
     * @param limit
     * @return
     */
    Map<String, Object> generateInfluentialEntitys(String entityName, int limit);

    Map<String, Long> generateTotalEntitys();
}
