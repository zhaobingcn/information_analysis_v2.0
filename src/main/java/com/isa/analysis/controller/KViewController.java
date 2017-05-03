package com.isa.analysis.controller;

import com.isa.analysis.sdn.entity.Keyword;
import com.isa.analysis.sdn.entity.Paper;
import com.isa.analysis.sdn.entity.QueryResult.KeywordAndInvolveTimes;
import com.isa.analysis.sdn.repository.KeywordRepository;
import com.isa.analysis.sdn.repository.PaperRepository;
import com.isa.analysis.service.KeywordViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by zhzy on 17-4-23.
 */
@Controller
public class KViewController {

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private KeywordViewService keywordViewService;

    @Autowired
    private KeywordRepository keywordRepository;

    @RequestMapping(value = "allKeywords")
    public String allKeywords(){
        return "/populartech";
    }

    @RequestMapping(value = "/allKeywords/showSvg")
    public @ResponseBody Map<String, Object> keywordsView(){
        return keywordViewService.generateKeywordView();
    }

    @RequestMapping(value = "/allKeywords/showSimilarKeywords")
    public @ResponseBody List<Map<String, Object>> similarKeywords(@RequestParam(value = "name", defaultValue = "FPGA", required = false)String name){
        return keywordRepository.getSimilarKeywords(name);
    }

    @RequestMapping(value = "/allKeywords/showRelatedPapers")
    public @ResponseBody List<Paper> relatedPapers(@RequestParam(value = "name", defaultValue = "FPGA", required = false)String name){
        return paperRepository.getPapersByKeywords(name);
    }
}
