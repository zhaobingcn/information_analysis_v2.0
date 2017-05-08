package com.isa.analysis.service;

import com.isa.analysis.sdn.entity.Author;
import com.isa.analysis.sdn.entity.Institution;
import com.isa.analysis.sdn.entity.Keyword;

import java.util.List;
import java.util.Map;

/**
 * Created by zhzy on 2017/1/5.
 */
public interface QueryPageService {

    /**
     * 全文搜索查找作者
     * @param name
     * @param institution
     * @return
     */
    List<Map<String, Object>> generateSearchAuthors(String name, String institution);

    List<Map<String, Object>> generateSearchInstitutions(String name);

    List<Map<String, Object>> generateSearchKeywords(String name);
}
