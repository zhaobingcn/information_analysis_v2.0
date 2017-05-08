package com.isa.analysis.controller;

import com.isa.analysis.sdn.entity.Author;
import com.isa.analysis.sdn.entity.Keyword;
import com.isa.analysis.service.QueryPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.Contended;

import java.util.List;
import java.util.Map;

/**
 * Created by zhzy on 2017/5/8.
 */
@Controller
public class KQueryController {

    @Autowired
    private QueryPageService queryPageService;

    @RequestMapping(value = "/queryOfKeyword")
    public String keywordQuery(){
        return "queryOfKeyword";
    }

    @RequestMapping(value = "/queryOfKeyword/commitQuery")
    public @ResponseBody List<Map<String, Object>> commitQuery(@RequestParam(value = "name", required = false, defaultValue = "合成")String name){
        return queryPageService.generateSearchKeywords(name);
    }
}
