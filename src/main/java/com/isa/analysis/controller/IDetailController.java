package com.isa.analysis.controller;

import com.isa.analysis.sdn.entity.QueryResult.KeywordAndInvolveTimes;
import com.isa.analysis.service.InstitutionInformationService;
import org.apache.commons.collections.map.HashedMap;
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

    /*
    * 科研机构的成果汇总
    * */
    @RequestMapping(value = "/tables/institution")
    public @ResponseBody List<List<String>> institutionAchievements(
            @RequestParam(value = "limit", required = false, defaultValue = "30")int limit,
            @RequestParam(value = "institutionId", required = false, defaultValue = "1")Long institutionId){
        Map<String,Integer> institutionPapersOnYear = institutionInformationService.generateInstitutionPublishedPapers(institutionId,limit);
        List<List<String>> ans = new ArrayList<>();
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

    /*
    * 科研机构的研究方向聚焦
    * */
    @RequestMapping(value = "/table/institutionPoint")
    public  @ResponseBody List<KeywordAndInvolveTimes> institutionInterest(
            @RequestParam(value = "limit", required = false, defaultValue = "30")int limit,
            @RequestParam(value = "institutionId", required = false, defaultValue = "1")Long institutionId
    ){
        return institutionInformationService.generateInstitutionKeywordAndInvolveTimes(institutionId,limit);
    }

    /*
    * 科研机构的合作机构以及合作次数
    *
    * */
    @RequestMapping(value = "/table/institutionCoo")
    public  @ResponseBody List<List<Map<String,Object>>> institutionCooperateTimes(
            @RequestParam(value = "limit", required = false, defaultValue = "30")int limit,
            @RequestParam(value = "institutionId", required = false, defaultValue = "1")Long institutionId
    ){
        Long id = new Long(1);
        return institutionInformationService.generateInstitutionAndCooperateTimes(id,limit);
    }

}
