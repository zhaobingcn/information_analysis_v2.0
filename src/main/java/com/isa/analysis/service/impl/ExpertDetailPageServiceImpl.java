package com.isa.analysis.service.impl;

import com.isa.analysis.restapi.httprepository.RestApiRepository;
import com.isa.analysis.sdn.entity.Author;
import com.isa.analysis.sdn.entity.Paper;
import com.isa.analysis.sdn.entity.QueryResult.AuthorAndWorkTogetherTimes;
import com.isa.analysis.sdn.entity.QueryResult.InstitutionAndCooperateTimes;
import com.isa.analysis.sdn.entity.QueryResult.KeywordAndInvolveTimes;
import com.isa.analysis.sdn.repository.*;
import com.isa.analysis.service.ExpertDetailPageService;
import org.apache.commons.collections.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zhzy on 2017/1/5.
 */
@Service
public class ExpertDetailPageServiceImpl implements ExpertDetailPageService {

    @Autowired
    private RestApiRepository restApiRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

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
    public Map<String, Object> generateKeywordsDetails(Long id) {
        List<KeywordAndInvolveTimes> keywordsDetail = keywordRepository.getKeywordsByAuthor(id);
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
        int resarchWidth=0, quoteCount=0, papersCount=0, resarchInfluence=0,cooperateAuthors=0;
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
        for(Author author: workTogetherAuthors){
                cooperateAuthors += restApiRepository.getDegreeOfNode(author.getId(), "all");
        }

        if(cooperateAuthors > 200){
            cooperateAuthors = 200;
        }
        Collection<Paper>  authorPapers = paperRepository.findByAuthor(name, institution);
        papersCount = authorPapers.size();
        if(papersCount > 30){
            papersCount = 30;
        }
        for(Paper paper: authorPapers){
            quoteCount += paper.getQuote();
        }
        if(quoteCount > 200){
            quoteCount = 200;
        }
        resarchInfluence = quoteCount + papersCount * 10;
        if(resarchInfluence > 200){
            resarchInfluence = 200;
        }
        Map<String, Object> abilityData = new HashMap<>();
        List<Object> data = new ArrayList<>();
        data.add(papersCount);
        data.add(quoteCount);
        data.add(rearchDepath);
        data.add(resarchWidth);
        data.add(cooperateAuthors);
        data.add(resarchInfluence);
        abilityData.put("data", data);
        return abilityData;
    }

    @Override
    public Map<String, Object> generateAuthorAbility(Long id) {
        int resarchWidth=0, quoteCount=0, papersCount=0, resarchInfluence=0,cooperateAuthors=0;
        double rearchDepath;
        List<KeywordAndInvolveTimes> keywords = keywordRepository.getKeywordsByAuthor(id);
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
        Collection<Author> workTogetherAuthors = authorRepository.getWorkTogetherAuthorsByAuthor(id);
        for(Author author: workTogetherAuthors){
            cooperateAuthors += restApiRepository.getDegreeOfNode(author.getId(), "all");
        }

        if(cooperateAuthors > 200){
            cooperateAuthors = 200;
        }
        Collection<Paper>  authorPapers = paperRepository.findByAuthor(id);
        papersCount = authorPapers.size();
        if(papersCount > 30){
            papersCount = 30;
        }
        for(Paper paper: authorPapers){
            quoteCount += paper.getQuote();
        }
        if(quoteCount > 200){
            quoteCount = 200;
        }
        resarchInfluence = quoteCount + papersCount * 10;
        if(resarchInfluence > 200){
            resarchInfluence = 200;
        }
        Map<String, Object> abilityData = new HashMap<>();
        List<Object> data = new ArrayList<>();
        data.add(papersCount);
        data.add(quoteCount);
        data.add(rearchDepath);
        data.add(resarchWidth);
        data.add(cooperateAuthors);
        data.add(resarchInfluence);
        abilityData.put("data", data);
        return abilityData;
    }

    @Override
    public List<Map<String, Object>> generateAuthorsPapersPages(String name, String institution, int skip, int limit) {
        List<Paper> papers = paperRepository.getPapersByAuthorWithPages(name, institution, skip, limit);
        List<Map<String, Object>> papersInMap = new ArrayList<>();
        for(Paper paper: papers){
            Map<String, Object> paperInMap = new HashMap<>();
            paperInMap.put("title", paper.getTitle());
            paperInMap.put("link", paper.getLink());
            paperInMap.put("quote", paper.getQuote());
            papersInMap.add(paperInMap);
        }
        return papersInMap;
    }

    @Override
    public List<Map<String, Object>> generateAuthorsPapers(String name, String institution) {
        Collection<Paper> papers = paperRepository.findByAuthor(name, institution);
        List<Map<String, Object>> papersInMap = new ArrayList<>();
        for(Paper paper: papers){
            Map<String, Object> paperInMap = new HashMap<>();
            paperInMap.put("title", paper.getTitle());
            paperInMap.put("link", paper.getLink());
            paperInMap.put("quote", paper.getQuote());
            papersInMap.add(paperInMap);
        }
        return papersInMap;
    }

    @Override
    public List<Map<String, Object>> generateAuthorsCooperate(String name, String institution) {
        List<AuthorAndWorkTogetherTimes> sortedCooperateAuthors = authorRepository.getSortedWorkTogetherAuthorsByAuthor(name, institution);
        List<Map<String, Object>> cooperateAuthors = new ArrayList<>();
        for(AuthorAndWorkTogetherTimes authorAndWorkTogetherTimes:sortedCooperateAuthors){
            Map<String, Object> author = new HashMap<>();
            author.put("name", authorAndWorkTogetherTimes.getAuthor().getName());
            author.put("institution", authorAndWorkTogetherTimes.getAuthor().getInstitution());
            author.put("times", authorAndWorkTogetherTimes.getTimes());
            cooperateAuthors.add(author);
        }
        return cooperateAuthors;
    }

    @Override
    public Map<String, Object> generateAuthorsCooperateInstitution(String name, String institution) {
        List<InstitutionAndCooperateTimes> institutionAndCooperateTimes = institutionRepository.getCooperateInstitutionByAuthor(name, institution);
        Map<String, Object> cooperateInstitution = new LinkedMap();
        for(InstitutionAndCooperateTimes ainstitution: institutionAndCooperateTimes){
            cooperateInstitution.put(ainstitution.getInstitution().getName(), ainstitution.getTimes());
        }
        return cooperateInstitution;
    }

    @Override
    public int generateAuthorsPapersCount(String name, String institution) {
        int papersCount = paperRepository.getPapersCountByAuthor(name, institution);
        return papersCount;
    }

    @Override
    public Map<Integer, ArrayList<Integer>> generateAuthorsAchievement(String name, String institution) {

        Collection<Paper> papers = paperRepository.findByAuthor(name, institution);
        Map<Integer, ArrayList<Integer>> authorsAchievement = new LinkedHashMap<>();
        for(int i=2006; i<2017; i++){
            ArrayList<Integer> countAndQuote = new ArrayList<>();
            countAndQuote.add(0);
            countAndQuote.add(0);
            authorsAchievement.put(i, countAndQuote);
        }
        for(Paper paper: papers){
            int year = Integer.parseInt(paper.getDate().substring(0,4));
            if(year >=2006 && year <=2016){
                authorsAchievement.get(year).set(0, authorsAchievement.get(year).get(0) + 1);
                authorsAchievement.get(year).set(1, authorsAchievement.get(year).get(1) + paper.getQuote());
            }
        }
        return authorsAchievement;
    }
}
