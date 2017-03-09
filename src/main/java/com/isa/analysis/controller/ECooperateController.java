package com.isa.analysis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hexu on 2016/12/30.
 */
@Controller
public class ECooperateController {
    @RequestMapping(value = "/ComparisonofExpert")
    public String eCooperate(){
        return "ComparisonofExpert";
    }

//    @RequestMapping(value = "/ComparisonofExpert/commitComparison")
//    public String cComparison (@RequestParam(name = "name1", required = false, defaultValue = "詹毅")String name,
//                               @RequestParam(name = "institution", required = false, defaultValue = "电子科技集团36所")String institution,
//
//                               )
//    {
//
//    }

}
