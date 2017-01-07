package com.isa.analysis.service.impl;

import com.isa.analysis.restapi.httprepository.RestApiRepository;
import com.isa.analysis.sdn.entity.Author;
import com.isa.analysis.sdn.entity.Paper;
import com.isa.analysis.sdn.entity.QueryResult.KeywordAndInvolveTimes;
import com.isa.analysis.sdn.repository.AuthorRepository;
import com.isa.analysis.sdn.repository.KeywordRepository;
import com.isa.analysis.sdn.repository.Neo4jTemplateRepository;
import com.isa.analysis.service.ExpertDetailPageService;
import com.isa.analysis.service.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zhzy on 2017/1/5.
 */
@Service
public class ExpertDetailPageServiceImpl implements ExpertDetailPageService {

    @Autowired
    private Neo4jTemplateRepository neo4jTemplateRepository;

    @Autowired
    private RestApiRepository restApiRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private MapUtil mapUtil;

    @Override
    public Map<String, Object> generateKeywordsDetails(String name, String institution) {
        List<KeywordAndInvolveTimes> keywordsDetail = keywordRepository.getKeywordsByAuthor(name, institution);
        List<Map<String, Object>> dataGroup = new ArrayList<>();
        for(KeywordAndInvolveTimes keywordDetail: keywordsDetail){
            dataGroup.add(mapUtil.map("name", keywordDetail.getKeyword().getName(), "value", keywordDetail.getTimes()));
        }
        Map<String, Object> finalExpertInterestData = new HashMap<>();
        finalExpertInterestData.put("data", dataGroup);
        return finalExpertInterestData;
    }

    @Override
    public Map<String, Object> generateAuthorAbility(String name, String institution) {
        int resarchWidth, quoteCount=0, papersCount, resarchInfluence;
        long cooperateAuthors;
        double rearchDepath;
        List<KeywordAndInvolveTimes> keywords = keywordRepository.getKeywordsByAuthor(name, institution);
        resarchWidth = keywords.size();
        if(resarchWidth > 50){
            resarchWidth = 50;
        }
        long allKeywordsCount = 0;
        for(KeywordAndInvolveTimes keywordDetail:keywords){
            allKeywordsCount += keywordDetail.getTimes();
        }
        rearchDepath = (double)allKeywordsCount/resarchWidth;
        if(rearchDepath > 2.0){
            rearchDepath = 2.0;
        }
        Collection<Author> workTogetherAuthors = authorRepository.getWorkTogetherAuthorsByAuthor(name, institution);
        cooperateAuthors = workTogetherAuthors.stream().map(author -> author.getId()).map(id -> {
            return restApiRepository.getDegreeOfNode(id, "all");
        }).reduce(0l , Long::sum);
        if(cooperateAuthors > 200){
            cooperateAuthors = 200;
        }
        Collection<Paper>
        return null;
    }

    @Override
    public List<Map<String, Object>> generateAuthorsPapersPages(String name, String institution, int skip, int limit) {
        return null;
    }

    @Override
    public List<Map<String, Object>> generateAuthorsPapers(String name, String institution) {
        return null;
    }

    @Override
    public List<Map<String, Object>> generateAuthorsCoorpeate(String name, String institution) {
        return null;
    }

    @Override
    public Map<String, Object> generateAuthorsCooperateInstitution(String name, String institution) {
        return null;
    }

    @Override
    public int generateAuthorsPapersCount(String name, String institution) {
        return 0;
    }
}
