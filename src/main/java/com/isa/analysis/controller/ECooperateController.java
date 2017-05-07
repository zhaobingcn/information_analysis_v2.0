package com.isa.analysis.controller;

import com.isa.analysis.sdn.entity.Author;
import com.isa.analysis.sdn.entity.QueryResult.InstitutionAndCooperateTimes;
import com.isa.analysis.sdn.entity.QueryResult.KeywordAndInvolveTimes;
import com.isa.analysis.sdn.repository.AuthorRepository;
import com.isa.analysis.sdn.repository.InstitutionRepository;
import com.isa.analysis.sdn.repository.KeywordRepository;
import com.isa.analysis.sdn.repository.Neo4jTemplateRepository;
import com.isa.analysis.service.ExpertDetailPageService;
import com.isa.analysis.service.RestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hexu on 2016/12/30.
 */
@Controller
public class ECooperateController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private Neo4jTemplateRepository neo4jTemplateRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private ExpertDetailPageService expertDetailPageService;

    @Autowired
    private RestApiService restApiService;

    @RequestMapping(value = "/ComparisonOfExpert/commitComparison")
    public String eComparison (Model model,
                               @RequestParam(value = "id1", required = false, defaultValue = "12")Long id1,
                               @RequestParam(value = "id2", required = false, defaultValue = "34")Long id2
                               )
    {

        //作者详情
        Author author1 = authorRepository.findById(id1);
        Author author2 = authorRepository.findById(id2);

        //top3研究方向,论文发表数,论文引用数
        List<Map<String, Object>> authorsComparsionInformations1 = neo4jTemplateRepository.getAuthorsComparsionInformations(id1);
        List<Map<String, Object>> authorsComparsionInformations2 = neo4jTemplateRepository.getAuthorsComparsionInformations(id2);

        //top5常用合作机构
        List<InstitutionAndCooperateTimes> authorsCooperateInstitutions1 = institutionRepository.getCooperateInstitutionByAuthorId(id1, 7);
        List<InstitutionAndCooperateTimes> authorsCooperateInstitutions2 = institutionRepository.getCooperateInstitutionByAuthorId(id2, 7);

        model.addAttribute("author1", author1);
        model.addAttribute("author2", author2);
        model.addAttribute("comparsion1", authorsComparsionInformations1);
        model.addAttribute("comparsion2", authorsComparsionInformations2);
        model.addAttribute("cooperateInstitutions1", authorsCooperateInstitutions1);
        model.addAttribute("cooperateInstitutions2", authorsCooperateInstitutions2);

        return "ComparisonofExpert";
    }

    @RequestMapping(value = "/ComparisonOfExpert/expertInterests")
    public @ResponseBody List<KeywordAndInvolveTimes> eInterests(@RequestParam(value = "id", required = false)Long id){
        return keywordRepository.getKeywordsByAuthor(id);
    }

    @RequestMapping(value = "/ComparisonOfExpert/abilityOfExpert")
    public @ResponseBody Map<String, Object> abilityOfExpert(
            @RequestParam(value = "id", required = false)Long id
    ){
        return expertDetailPageService.generateAuthorAbility(id);
    }

    @RequestMapping(value = "/ComparisonOfExpert/cooperateOfAuthor")
    public @ResponseBody
    Map<String, Object> cooperateRelation(
            @RequestParam(value = "id", required = false, defaultValue = "63")Long id,
            @RequestParam(value = "depath", required = false, defaultValue = "3")int depath
    ){
        return restApiService.generateWorkTogetherGraph(id, depath);
    }

    @RequestMapping(value = "/ComparisonOfExpert/achievements")
    public @ResponseBody Map<String, Object> authorsAchievements(@RequestParam(value = "id1", required = false, defaultValue = "63")Long id1
                                                                 ,@RequestParam(value = "id2", required = false, defaultValue = "65")Long id2
    ){
        Map<Integer, ArrayList<Integer>> authorAchievement1 = expertDetailPageService.generateAuthorsAchievement(id1);
        Map<Integer, ArrayList<Integer>> authorAchievement2 = expertDetailPageService.generateAuthorsAchievement(id2);
        List<Integer> author1QuoteInYear = new ArrayList<>();
        List<Integer> author1PublishInYear = new ArrayList<>();

        List<Integer> author2QuoteInYear = new ArrayList<>();
        List<Integer> author2PublishInYear = new ArrayList<>();

        for(Map.Entry<Integer, ArrayList<Integer>> achievementInYear: authorAchievement1.entrySet()){
            author1QuoteInYear.add(achievementInYear.getValue().get(1));
            author1PublishInYear.add(achievementInYear.getValue().get(0));
        }

        for(Map.Entry<Integer, ArrayList<Integer>> achievementInYear: authorAchievement2.entrySet()){
            author2QuoteInYear.add(achievementInYear.getValue().get(1));
            author2PublishInYear.add(achievementInYear.getValue().get(0));
        }
        Map<String, Object> finalAchievementData = new HashMap<>();
        finalAchievementData.put("author1QuoteInYear", author1QuoteInYear);
        finalAchievementData.put("author1PublishInYear", author1PublishInYear);
        finalAchievementData.put("author2QuoteInYear", author2QuoteInYear);
        finalAchievementData.put("author2PublishInYear", author2PublishInYear);
        return  finalAchievementData;
    }

}
