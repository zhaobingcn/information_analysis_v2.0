package com.isa.analysis.service.impl;

import com.isa.analysis.sdn.entity.QueryResult.KeywordAndInvolveTimes;
import com.isa.analysis.sdn.repository.KeywordRepository;
import com.isa.analysis.service.KeywordViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhzy on 17-4-24.
 */
@Service
public class KeywordViewServiceImpl implements KeywordViewService{

    @Autowired
    private KeywordRepository keywordRepository;

    @Override
    public Map<String, Object> generateKeywordView() {
        List<Map<Long, Integer>> keywordsPartition = keywordRepository.getKeywordsPartition();

        int sum = 0;
        for(Map<Long, Integer> partition: keywordsPartition){
            sum = sum + partition.get("score");
        }
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> resultChildren = new ArrayList<>();
        for(Map<Long, Integer> partition: keywordsPartition){
            int limit = (int)(partition.get("score")*1.0 / sum * 2000);
            List<KeywordAndInvolveTimes> keywordsList = keywordRepository.getKeywordsByPartition((long)partition.get("partition"), limit);
            Map<String, Object> category = new HashMap<>();
            category.put("name", keywordsList.get(0).getKeyword().getName());
            List<Map<String, Object>> children = new ArrayList<>();
            for(KeywordAndInvolveTimes aKeyword: keywordsList){
                Map<String, Object> keyword = new HashMap<>();
                keyword.put("name", aKeyword.getKeyword().getName());
                keyword.put("size", aKeyword.getTimes());
                children.add(keyword);
            }
            category.put("children", children);
            resultChildren.add(category);
        }
        result.put("name", "研究方向汇总");
        result.put("children", resultChildren);
        return result;
    }
}
