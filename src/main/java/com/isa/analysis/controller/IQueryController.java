package com.isa.analysis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhzy on 2017/5/8.
 */
@Controller
public class IQueryController {
    @RequestMapping(value = "/queryOfInstitution")
    public String institutionQuery(){
        return "queryOfInstitution";
    }
}
