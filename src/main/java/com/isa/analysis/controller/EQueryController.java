package com.isa.analysis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hexu on 2016/12/30.
 */
@Controller
public class EQueryController {
    @RequestMapping(value = "/queryOfExpert")
    public String expertQuery(){
        return "queryOfExpert";
    }
}
