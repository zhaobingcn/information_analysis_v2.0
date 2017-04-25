package com.isa.analysis.controller;

import com.isa.analysis.sdn.entity.QueryResult.KeywordAndInvolveTimes;
import com.isa.analysis.service.ComparsionOfInstitutionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhzy on 17-3-21.
 */
@Controller
public class IComparsionController {

    @Autowired
    private ComparsionOfInstitutionsService comparsionOfInstitutionsService;

    @RequestMapping(value = "/ComparisonofInstitutions")
    public String blank(){
        return "/ComparisonofInstitutions";
    }


    /*
    * 科研机构的研究方向聚焦
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
