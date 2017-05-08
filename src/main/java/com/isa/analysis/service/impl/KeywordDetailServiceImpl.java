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
        String keywordName = keywordRepository.findOne(id).getName().toString();
        List<Object> series = new ArrayList<>();
        List<Object> years = new ArrayList<>();
        long maxAuthorsCount = 0;
        long maxInstitutionsCount = 0;
        for(int year=startYear; year<=endYear; year++){
            List<Object> inYear = new ArrayList<>();
            long authorsCount = neo4jTemplateRepository.getKeywordRelatedAuthorsCount(year, id);
            inYear.add(authorsCount);
            if(authorsCount > maxAuthorsCount){
                maxAuthorsCount = authorsCount;
            }
            long institutionsCount = neo4jTemplateRepository.getKeywordRelatedInstitutionsCount(year, id);
            inYear.add(institutionsCount);
            if(institutionsCount > maxInstitutionsCount){
                maxInstitutionsCount = institutionsCount;
            }
            inYear.add(neo4jTemplateRepository.getKeywordRelatedPapersCount(year, id));
            inYear.add(keywordName);
            inYear.add(year);
            List<Object> yearList = new ArrayList<>();
            yearList.add(inYear);
            series.add(yearList);
            years.add(year);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("keywords", new String[]{keywordName});
        map.put("timeline", years);
        map.put("series", series);
        map.put("maxAuthorsCount", maxAuthorsCount);
        map.put("maxInstitutionsCount", maxInstitutionsCount);
        return map;
    }
}
