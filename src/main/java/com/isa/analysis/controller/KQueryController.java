package com.isa.analysis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.Contended;

/**
 * Created by zhzy on 2017/5/8.
 */
@Controller
public class KQueryController {
    @RequestMapping(value = "/queryOfKeyword")
    public String keywordQuery(){
        return "queryOfKeyword";
    }
}
