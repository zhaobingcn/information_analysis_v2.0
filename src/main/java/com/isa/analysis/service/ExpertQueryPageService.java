package com.isa.analysis.service;

import com.isa.analysis.sdn.entity.Author;

import java.util.List;

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
    List<Author> generateSearchAuthors(String name, String institution);
}
