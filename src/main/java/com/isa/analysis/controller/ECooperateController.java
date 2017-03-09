package com.isa.analysis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hexu on 2016/12/30.
 */
@Controller
public class ECooperateController {
    @RequestMapping(value = "/ComparisonofExpert")
    public String eCooperate(){
        return "ComparisonofExpert";
    }
}
