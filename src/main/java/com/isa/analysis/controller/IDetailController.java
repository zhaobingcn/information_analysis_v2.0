package com.isa.analysis.controller;

import com.isa.analysis.service.InstitutionInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhzy on 17-3-21.
 */
@Controller
public class IDetailController {
    @Autowired
    private InstitutionInformationService institutionInformationService;

    @RequestMapping(value = "/tables")
    public String tables(){
        return "tables";
    }

    @RequestMapping(value = "/tables/institution")
    public @ResponseBody List<List<String>> influentialExperts(
            @RequestParam(value = "limit", required = false, defaultValue = "30")int limit,
            @RequestParam(value = "institutionId", required = false, defaultValue = "1")Long institutionId){
        Map<String,Integer> institutionPapersOnYear = institutionInformationService.generateInstitutionPublishedPapers(institutionId,limit);
        List<List<String>> ans = new ArrayList<List<String>>();
        for (Map.Entry<String,Integer> entry:institutionPapersOnYear.entrySet()
             ) {
            List<String> an = new ArrayList<>();
            an.add(entry.getKey());
            an.add(entry.getValue().toString());
            an.add("论文发表量");
            ans.add(an);
        }
        return ans;
    }
    //public String blank(){
    //    return "/tables";
    //}
}
