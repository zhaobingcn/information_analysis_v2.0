package com.isa.analysis.service.impl;

import com.isa.analysis.sdn.entity.Keyword;
import com.isa.analysis.sdn.entity.Similar;
import com.isa.analysis.sdn.repository.KeywordRepository;
import com.isa.analysis.sdn.repository.Neo4jTemplateRepository;
import com.isa.analysis.sdn.repository.SimilarRepository;
import com.isa.analysis.service.KeywordDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.SynthesizedAnnotation;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zhzy on 17-5-2.
 */
@Service
public class KeywordDetailServiceImpl implements KeywordDetailService{

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private SimilarRepository similarRepository;

    @Autowired
    private Neo4jTemplateRepository neo4jTemplateRepository;


    @Override
    public Map<String, Object> generateKeywordsTrend(Long id) {

        int startYear = 2006;
        int endYear = 2016;
        for(int i=startYear; i<=endYear; i++){
            List<Object> inYear = new ArrayList<>();
            inYear.add(neo4jTemplateRepository.);
        }
        return null;
    }
}
