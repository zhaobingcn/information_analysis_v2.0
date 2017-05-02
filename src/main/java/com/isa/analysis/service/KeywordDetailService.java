package com.isa.analysis.service;

import java.util.Map;

/**
 * Created by zhzy on 17-5-2.
 */
public interface KeywordDetailService {


    /**
     * 生成keyword页面的而第一个图
     * @return
     */
    Map<String, Object> generateKeywordsRelationship(Long id);
}
