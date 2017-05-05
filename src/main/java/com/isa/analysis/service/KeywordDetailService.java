package com.isa.analysis.service;

import java.util.Map;

/**
 * Created by zhzy on 17-5-2.
 */
public interface KeywordDetailService {


    /**
     * 生成word页面关键词演化图形数据
     */

    Map<String, Object> generateKeywordsTrend(Long id);
}
