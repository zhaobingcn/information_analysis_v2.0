package com.isa.analysis.service;

import com.isa.analysis.sdn.entity.Author;

import java.util.List;
import java.util.Map;

/**
 * Created by zhzy on 2017/1/5.
 */
public interface ExpertQueryPageService {

    /**
     * 全文搜索查找作者
     * @param name
     * @param institution
     * @return
     */
    List<Map<String, Object>> generateSearchAuthors(String name, String institution);
}
