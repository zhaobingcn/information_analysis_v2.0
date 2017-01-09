package com.isa.analysis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hexu on 2016/12/30.
 */
@Controller
public class EQueryController {
    @RequestMapping(value = "/queryOfExpert")
    public String expertQuery(){
        return "queryOfExpert";
    }

    @RequestMapping(value = "/queryOfExpert/commitQuery")
    public @ResponseBody String commitQuery(){

        return null;
    }
}
