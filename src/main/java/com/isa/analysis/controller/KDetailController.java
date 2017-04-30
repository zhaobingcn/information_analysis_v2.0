package com.isa.analysis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhzy on 17-4-30.
 */
@Controller
public class KDetailController {


    @RequestMapping(value = "keywordDetail")
    public String keywordDetail(){
        return "keyword";
    }
}
