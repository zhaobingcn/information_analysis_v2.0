package com.isa.analysis.service.impl;

import com.isa.analysis.sdn.repository.Neo4jTemplateRepository;
import com.isa.analysis.service.ExpertDetailPageService;
import com.isa.analysis.service.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhzy on 2017/1/5.
 */
@Service
public class ExpertDetailPageServiceImpl implements ExpertDetailPageService {

    @Autowired
    private Neo4jTemplateRepository neo4jTemplateRepository;

    @Autowired
    private MapUtil mapUtil;

    @Override
    public Map<String, Object> generateKeywordsDetails(String name, String institution) {
        Map<String, Object> keywordsDetail = neo4jTemplateRepository.getKeywordsByAuthor(name, institution);
        List<Map<String, Object>> dataGroup = new ArrayList<>();
        for(Map.Entry<String, Object> oneKeyword:keywordsDetail.entrySet()){
            dataGroup.add(mapUtil.map("name", oneKeyword.getKey(), "value", oneKeyword.getValue()));
        }
        Map<String, Object> finalExpertInterestData = new HashMap<>();
        finalExpertInterestData.put("data", dataGroup);
        return finalExpertInterestData;
    }
}
