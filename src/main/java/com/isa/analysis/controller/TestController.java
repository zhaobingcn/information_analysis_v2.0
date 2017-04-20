package com.isa.analysis.controller;

import com.isa.analysis.sdn.entity.*;
import com.isa.analysis.sdn.entity.QueryResult.AuthorAndWorkTogetherTimes;
import com.isa.analysis.sdn.repository.*;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hexu on 2017/1/6.
 */
@Controller
public class TestController {

    @Autowired
    private PaperRepository paperRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private WorkTogetherRepository workTogetherRepository;
    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private Neo4jTemplateRepository neo4jTemplateRepository;


    @RequestMapping(value = "/test")
    public @ResponseBody Collection<Paper> test(){
        return paperRepository.findByAuthor("詹毅", "电子科技集团36所");
    }

    @RequestMapping(value = "/test1")
    public @ResponseBody List<AuthorAndWorkTogetherTimes> test1(){
        return authorRepository.getSortedWorkTogetherAuthorsByAuthor("詹毅", "电子科技集团36所");
    }

    @RequestMapping(value = "/test2")
    public @ResponseBody String test2(){
        return  institutionRepository.getCooperateInstitutionByAuthor("詹毅", "电子科技集团36所").toString();
    }

    @RequestMapping(value = "/test3")
    public @ResponseBody String test3(){
        return workTogetherRepository.findWorkGetherByAuthor("詹毅", "电子科技集团36所").toString();
    }

    @RequestMapping(value = "/test4")
    public @ResponseBody String test4(){
        return keywordRepository.getKeywordsByAuthor("詹毅", "电子科技集团36所").toString();
    }

    @RequestMapping(value = "/test5")
    public @ResponseBody Map<String, Object> test5() {
        Map<String, String> zhaobing = new HashMap<>();
        zhaobing.put("name", "zhaobing");
        zhaobing.put("institution", "北京邮电大学");
        Map<String, Object> result = new HashMap<>();
        result.put("result", neo4jTemplateRepository.createNodeOfAuthor(zhaobing));
        return result;
    }
}
