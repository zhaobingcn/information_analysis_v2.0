package com.isa.analysis.controller;

import com.isa.analysis.service.RestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by zhzy on 17-4-30.
 */
@Controller
public class KDetailController {

    @Autowired
    private RestApiService restApiService;

    @RequestMapping(value = "/keywordDetail")
    public String keywordDetail(){
        return "keyword";
    }

    @RequestMapping(value = "/keywordDetail/keywordsRelationship")
    public @ResponseBody Map<String, Object> keywordsRelationship(@RequestParam(name = "id", required = false, defaultValue = "45")Long id,
                                             @RequestParam(name = "depath", required = false, defaultValue = "2")int depath){
        return restApiService.generateSimilarGraph(id, depath);
    }
}