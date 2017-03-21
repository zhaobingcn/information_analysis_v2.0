package com.isa.analysis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhzy on 17-3-21.
 */
@Controller
public class IDetailController {
    @RequestMapping(value = "/tables")
    public String blank(){
        return "/tables";
    }
}
