package com.isa.analysis.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by zhzy on 2017/1/5.
 */
public interface ExpertDetailPageService {

    /**
     * 包装成echarts可以用的json格式数据
     *
     * @return json格式数据
     */
    Map<String, Object> generateKeywordsDetails(String name, String institution);
}
