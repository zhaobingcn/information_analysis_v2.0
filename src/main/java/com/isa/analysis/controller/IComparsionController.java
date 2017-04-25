package com.isa.analysis.controller;

import com.isa.analysis.sdn.entity.QueryResult.KeywordAndInvolveTimes;
import com.isa.analysis.service.ComparsionOfInstitutionsService;
import com.isa.analysis.service.InstitutionInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by zhzy on 17-3-21.
 */
@Controller
public class IComparsionController {

    @Autowired
    private ComparsionOfInstitutionsService comparsionOfInstitutionsService;
    @Autowired
    private InstitutionInformationService institutionInformationService;



    /*
    * 网页传来两个机构的ID
    * */
    @RequestMapping(value = "/ComparisonofInstitutions")
    public String blank(Model model,
                        @RequestParam(value = "limit", required = false, defaultValue = "30")int limit,
                        @RequestParam(value = "firstInstitutionId", required = false, defaultValue = "1")Long firstInstitutionId,
                        @RequestParam(value = "secondInstitutionId", required = false, defaultValue = "117")Long secondInstitutionId){
        List<KeywordAndInvolveTimes> firstInstitutionKeywordAndInvolveTimesList = comparsionOfInstitutionsService.generateInstitutionKeywordAndInvolveTimes(firstInstitutionId,3);
        List<KeywordAndInvolveTimes> secondInstitutionKeywordAndInvolveTimesList = comparsionOfInstitutionsService.generateInstitutionKeywordAndInvolveTimes(secondInstitutionId,3);

        String firstInstitutionName = comparsionOfInstitutionsService.generateInstitutionName(firstInstitutionId);
        String secondInstitutionName = comparsionOfInstitutionsService.generateInstitutionName(secondInstitutionId);
        model.addAttribute("firstInstitutionKeywordAndInvolveTimesList",firstInstitutionKeywordAndInvolveTimesList);
        model.addAttribute("secondInstitutionKeywordAndInvolveTimesList",secondInstitutionKeywordAndInvolveTimesList);
        model.addAttribute("firstInstitutionName",firstInstitutionName);
        model.addAttribute("secondInstitutionName",secondInstitutionName);

        return "/ComparisonofInstitutions";
    }


    @RequestMapping(value = "/ComparisonofInstitutions/test")
    public  @ResponseBody
    List<Integer> test(
            @RequestParam(value = "limit", required = false, defaultValue = "30")int limit,
            @RequestParam(value = "firstInstitutionId", required = false, defaultValue = "1")Long firstInstitutionId,
            @RequestParam(value = "secondInstitutionId", required = false, defaultValue = "117")Long secondInstitutionId
    ){
        Map<String, Integer> firstInstitution = comparsionOfInstitutionsService.generateInstitutionPublishedPapers(firstInstitutionId,limit);
        Map<String, Integer> secondInstitution = comparsionOfInstitutionsService.generateInstitutionPublishedPapers(secondInstitutionId,limit);
        
        List<Integer> numOfFirstInstitutionPaper = new ArrayList<>();
        for (Map.Entry entry : firstInstitution.entrySet()
             ) {
            numOfFirstInstitutionPaper.add((Integer)entry.getValue());
        }

        return numOfFirstInstitutionPaper;
    }

    /*
    * 科研机构的研究方向对比
    * */
    @RequestMapping(value = "/ComparisonofInstitutions/firstInstitutionInterest")
    public  @ResponseBody
    List<KeywordAndInvolveTimes> firstInstitutionInterest(
            @RequestParam(value = "limit", required = false, defaultValue = "30")int limit,
            @RequestParam(value = "institutionId", required = false, defaultValue = "1")Long institutionId
    ){
        return comparsionOfInstitutionsService.generateInstitutionKeywordAndInvolveTimes(institutionId,limit);
    }

    @RequestMapping(value = "/ComparisonofInstitutions/secondInstitutionInterest")
    public  @ResponseBody
    List<KeywordAndInvolveTimes> secondInstitutionInterest(
            @RequestParam(value = "limit", required = false, defaultValue = "30")int limit,
            @RequestParam(value = "institutionId", required = false, defaultValue = "117")Long institutionId
    ){
        return comparsionOfInstitutionsService.generateInstitutionKeywordAndInvolveTimes(institutionId,limit);
    }
}
