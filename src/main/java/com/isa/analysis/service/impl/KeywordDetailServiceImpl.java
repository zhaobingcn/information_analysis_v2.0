package com.isa.analysis.service.impl;

import com.isa.analysis.sdn.entity.Keyword;
import com.isa.analysis.sdn.entity.Similar;
import com.isa.analysis.sdn.repository.KeywordRepository;
import com.isa.analysis.sdn.repository.SimilarRepository;
import com.isa.analysis.service.KeywordDetailService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public Map<String, Object> generateKeywordsRelationship(Long id) {

        List<List<Keyword>> keywords = keywordRepository.getRelatedKeywordsWithDepath(id);
        List<List<Similar>> relationships = similarRepository.getRelationshipsWithDeapth(id);
        /**
         * 两个结果list  node的和relationship的
         */
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> rels = new ArrayList<>();
        /**
         * 用来查询node和relationship是否多次出现过
         */
        Map<Long, Integer> checkNodes = new HashMap<>();
        HashSet<Long> checkRels = new HashSet<>();
        for(int i=0; i<relationships.size(); i++){

            for(int j=0; j<relationships.get(i).size(); j++){
                System.out.println(relationships.get(i).get(j).getId());
            }
        }
        return null;
    }
}
