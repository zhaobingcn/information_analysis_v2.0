package com.isa.analysis.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hexu on 2016/12/30.
 */
public class BlankController {
    @RequestMapping(value = "/blank")
    public String blank(){
        return "blank";
    }
}
