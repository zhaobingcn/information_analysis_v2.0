package com.isa.analysis.controller;

import com.isa.analysis.sdn.entity.Institution;
import com.isa.analysis.service.QueryPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by zhzy on 2017/5/8.
 */
@Controller
public class IQueryController {

    @Autowired
    private QueryPageService queryPageService;

    @RequestMapping(value = "/queryOfInstitution")
    public String institutionQuery(){
        return "queryOfInstitution";
    }

    @RequestMapping(value = "/queryOfInstitution/commitQuery")
    public @ResponseBody List<Map<String, Object>> commitQuery(@RequestParam(value = "name", required = false, defaultValue = "电子科技集团36")String name){

        return queryPageService.generateSearchInstitutions(name);
    }
}
