package com.isa.analysis.controller;

import com.isa.analysis.sdn.entity.Paper;
import com.isa.analysis.sdn.repository.PaperRepository;
import com.isa.analysis.service.KeywordDetailService;
import com.isa.analysis.service.RestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by zhzy on 17-4-30.
 */
@Controller
public class KDetailController {

    @Autowired
    private RestApiService restApiService;

    @Autowired
    private KeywordDetailService keywordDetailService;

    @Autowired
    private PaperRepository paperRepository;

    @RequestMapping(value = "/keywordDetail")
    public String keywordDetail(@RequestParam(name = "name", required = false, defaultValue = "48")String name){
        List<Paper> papers = paperRepository.getPapersByKeywords(name, 8);
        return "keyword";
    }

    @RequestMapping(value = "/keywordDetail/keywordsRelationship")
    public @ResponseBody Map<String, Object> keywordsRelationship(@RequestParam(name = "id", required = false, defaultValue = "45")Long id,
                                             @RequestParam(name = "depath", required = false, defaultValue = "2")int depath){
        return restApiService.generateSimilarGraph(id, depath);
    }

    @RequestMapping(value = "/keywordDetail/keywordTrend")
    public @ResponseBody Map<String, Object> keywordTrend(@RequestParam(name = "id", required = false, defaultValue = "48")Long id){
        return keywordDetailService.generateKeywordsTrend(id);
    }


}
