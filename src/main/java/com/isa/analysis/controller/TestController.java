package com.isa.analysis.controller;

import com.isa.analysis.sdn.entity.*;
import com.isa.analysis.sdn.entity.QueryResult.AuthorAndWorkTogetherTimes;
import com.isa.analysis.sdn.repository.*;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
        zhaobing.put("name", "刘静");
        zhaobing.put("institution", "电子科技集团36所");
        Map<String, Object> result = new HashMap<>();
        result.put("result", neo4jTemplateRepository.createNodeOfAuthor(zhaobing));
        return result;
    }

    @RequestMapping(value = "/test6")
    public @ResponseBody Map<String, Object> test6() {
        Map<String, Object> zhaobingPaper = new HashMap<>();

        zhaobingPaper.put("title", "今天的另一篇论文");
        zhaobingPaper.put("quote", 6);
        zhaobingPaper.put("link", "123456");
        zhaobingPaper.put("date", "20151108");

        Map<String, Object> result = new HashMap<>();
        result.put("result", neo4jTemplateRepository.createNodeOfPaper(zhaobingPaper));
        return result;
    }

    @RequestMapping(value = "test7")
    public @ResponseBody Map<String, Object> test7(){
        Map<String, String> zhaobingInstitution = new HashMap<>();
        zhaobingInstitution.put("name", "赵炳的学校");
        zhaobingInstitution.put("location", "北京");
        Map<String, Object> result = new HashMap<>();
        result.put("result", neo4jTemplateRepository.createNodeOfInstitution(zhaobingInstitution));
        return result;
    }

    @RequestMapping(value = "test8")
    public @ResponseBody Map<String, Object> test8(){
        Map<String, Object> result = new HashMap<>();
        result.put("result", neo4jTemplateRepository.createNodeOfKeyword("赵炳FPGA"));
        return result;
    }

    @RequestMapping(value = "test9")
    public @ResponseBody Map<String, Object> test9(){

        Map<String, String> zhaobingJournal = new HashMap<>();
        zhaobingJournal.put("name", "赵炳的杂志");
        Map<String, Object> result = new HashMap<>();
        result.put("result", neo4jTemplateRepository.createNodeOfJournal(zhaobingJournal));
        return result;
    }

    @RequestMapping(value = "test10")
    public @ResponseBody Map<String, Object> test10(){

        long id1 = 234443l;
        long id2 = 234441l;

        int weight = 1;

        String relationshipType = "related";

        Map<String, Object> result = new HashMap<>();
        result.put("result", neo4jTemplateRepository.createRelationship(id1, id2, relationshipType, weight));
        return result;
    }


}
