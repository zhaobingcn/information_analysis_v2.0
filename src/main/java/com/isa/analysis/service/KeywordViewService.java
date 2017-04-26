package com.isa.analysis.service;

import java.util.Map;

/**
 * Created by zhzy on 17-4-24.
 */
public interface KeywordViewService {

     /**
      * 生成关键词整体视图页面
      * @return
      */
    Map<String, Object> generateKeywordView();
}
