package com.isa.analysis.controller;

import com.isa.analysis.service.QueryPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by hexu on 2016/12/30.
 */
@Controller
public class EQueryController {

    @Autowired
    private QueryPageService queryPageService;

    @RequestMapping(value = "/queryOfExpert")
    public String expertQuery(){
        return "queryOfExpert";
    }

    @RequestMapping(value = "/queryOfExpert/commitQuery")
    public @ResponseBody List<Map<String, Object>> commitQuery(@RequestParam(value = "name", required = false)String name,
                                                               @RequestParam(value = "institution", required = false)String institution
                                                               ){
        return queryPageService.generateSearchAuthors(name, institution);
    }
}
