package com.isa.analysis.controller;

import com.isa.analysis.service.IndexPageService;
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
public class IndexController {
    @Autowired
    private IndexPageService indexPageService;

    @RequestMapping(value = "/pages/wordsCloud")
    public @ResponseBody
    Map<String, Object> wordsCloud(@RequestParam(value = "limit", required = false)int limit){
        String entityName = "Keyword";
        return indexPageService.generateInfluentialEntitys(entityName, limit);
    }

    @RequestMapping(value = "/pages/influentialExperts")
    public @ResponseBody Map<String, Object> influentialExperts(@RequestParam(value = "limit", required = false)int limit){
        String entityName = "Author";
        return indexPageService.generateInfluentialEntitys(entityName, limit);
    }

    @RequestMapping(value = "/pages/influentialInstitutions")
    public @ResponseBody Map<String, Object> influentialInstitutions(@RequestParam(value = "limit", required = false)int limit){
        String entityName = "Institution";
        return indexPageService.generateInfluentialEntitys(entityName, limit);
    }

    @RequestMapping(value = "/pages/influentialJournals")
    public @ResponseBody Map<String, Object> influentialJournals(@RequestParam(value = "limit", required = false)int limit){
        String entityName = "Journal";
        return indexPageService.generateInfluentialEntitys(entityName, limit);
    }

    @RequestMapping(value = "/index")
    public String index(Map<String, Long> model, Model models){
        List<Map<String, Object>> list = new ArrayList<>();
        for(int i=0; i<5; i++){
            Map<String, Object> user = new HashMap<>();
            user.put("name", "zhaobing");
            list.add(user);
        }
        models.addAttribute("userlist", list);
        Map<String, Long> totalEntitys = indexPageService.generateTotalEntitys();
        model.put("institutions", totalEntitys.get("institutions"));
        model.put("papers", totalEntitys.get("papers"));
        model.put("authors", totalEntitys.get("authors"));
        model.put("keywords", totalEntitys.get("keywords"));
        return "index";
    }
}
