package com.isa.analysis.controller;

import com.isa.analysis.restapi.httprepository.RestApiRepository;
import com.isa.analysis.service.ExpertDetailPageService;
import com.isa.analysis.service.IndexPageService;
import com.isa.analysis.service.RestApiService;
import org.neo4j.ogm.json.JSONObject;
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
public class EDetailController {

    @Autowired
    private RestApiRepository restApiRepository;

    @Autowired
    private ExpertDetailPageService expertDetailPageService;

    @Autowired
    private RestApiService restApiService;

    @RequestMapping(value = "/detailOfExpert")
    public String eDetail(Model model,
                          @RequestParam(name = "name", required = false, defaultValue = "詹毅")String name,
                          @RequestParam(name = "institution", required = false, defaultValue = "电子科技集团36所")String institution
    ){
        int skip=0, limit=8;
        List<Map<String, Object>> authorsPapersList = expertDetailPageService.generateAuthorsPapersPages(name, institution, skip, limit);
        List<Map<String, Object>> cooperateAuthorsList = expertDetailPageService.generateAuthorsCooperate(name, institution);
        Map<String, Object> cooperateInstitutionsList = expertDetailPageService.generateAuthorsCooperateInstitution(name, institution);
        model.addAttribute("authorsPapersList", authorsPapersList);
        model.addAttribute("cooperateAuthorsList",cooperateAuthorsList);
        model.addAttribute("cooperateInstitutionsList", cooperateInstitutionsList);
        List<Map<String, Object>> allAuthorsPapers = expertDetailPageService.generateAuthorsPapers(name, institution);
        Map<String, Object> authorsDetail = new HashMap<>();
        int authorsPapersCount = allAuthorsPapers.size();
        int quote = 0;
        for(Map<String, Object> paper: allAuthorsPapers){
            quote += Integer.parseInt(paper.get("quote").toString());
        }
        authorsDetail.put("name", name);
        authorsDetail.put("institution", institution);
        authorsDetail.put("quote", quote);
        authorsDetail.put("authorsPapersCount", authorsPapersCount);

        model.addAttribute("authorsDetail", authorsDetail);
        return "detailOfExpert";
    }

    @RequestMapping(value = "/detailOfExpert/cooperateOfAuthor")
    public @ResponseBody
    Map<String, Object> cooperateRelation(
            @RequestParam(value = "name", required = false, defaultValue = "詹毅")String name,
            @RequestParam(value = "institution", required = false, defaultValue = "电子科技集团36所")String institution,
            @RequestParam(value = "depath", required = false, defaultValue = "3")int depath
    ){
        return restApiService.generateWorkTogetherGraph(name, institution, depath);
    }

    @RequestMapping(value = "/detailOfExpert/InterestOfExpert")
    public @ResponseBody Map<String, Object> interestOfExpert(
            @RequestParam(value = "name", required = false)String name,
            @RequestParam(value = "institution", required = false)String institution
    ){
        return expertDetailPageService.generateKeywordsDetails(name, institution);
    }

    @RequestMapping(value = "/detailOfExpert/abilityOfExpert")
    public @ResponseBody Map<String, Object> abilityOfExpert(
            @RequestParam(value = "name", required = false)String name,
            @RequestParam(value = "institution", required = false)String institution
    ){
        return expertDetailPageService.generateAuthorAbility(name, institution);
    }

    @RequestMapping(value = "/detailOfExpert/papersWithPages")
    public @ResponseBody Map<String, Object> papersWithPages(
            @RequestParam(value = "name", required = false)String name,
            @RequestParam(value = "institution", required = false)String institution,
            @RequestParam(value = "skip", required = false)int skip,
            @RequestParam(value = "limit", required = false)int limit
    ){
        List<Map<String, Object>> authorsPapersList = expertDetailPageService.generateAuthorsPapersPages(name, institution, skip, limit);
        Map<String, Object> finalPapersData = new HashMap<>();
        finalPapersData.put("data", authorsPapersList);
        return  finalPapersData;
    }

    @RequestMapping(value = "/detailOfExpert/authorsAchievement")
    public @ResponseBody Map<String, Object> authorsAchievement(
            @RequestParam(value = "name", required = false, defaultValue = "詹毅")String name,
            @RequestParam(value = "institution", required = false, defaultValue = "电子科技集团36所")String institution
    ){
        Map<Integer, ArrayList<Integer>> authorsAchievement = expertDetailPageService.generateAuthorsAchievement(name, institution);
        List<Integer> quoteInYear = new ArrayList<>();
        List<Integer> publishInYear = new ArrayList<>();
        for(Map.Entry<Integer, ArrayList<Integer>> achievementInYear: authorsAchievement.entrySet()){
            quoteInYear.add(achievementInYear.getValue().get(1));
            publishInYear.add(achievementInYear.getValue().get(0));
        }
        Map<String, Object> finalAchievementData = new HashMap<>();
        finalAchievementData.put("quote", quoteInYear);
        finalAchievementData.put("publish", publishInYear);
        return finalAchievementData;
    }


}
