package com.isa.analysis.controller;

import com.isa.analysis.sdn.entity.Keyword;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by zhzy on 17-4-23.
 */
@Controller
public class KViewController {

    @RequestMapping(value = "/allKeywords")
    public String index(){
        return "populartech";
    }
}
