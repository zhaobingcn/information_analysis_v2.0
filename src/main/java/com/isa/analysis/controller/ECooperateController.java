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

    @RequestMapping(value = "/ComparisonofExpert/commitComparison")
    public String cComparison (@RequestParam(value = "id1", required = false)Long id1,
                               @RequestParam(value = "id2", required = false)Long id2
                               )
    {
        return "ComparisonofExpert";
    }

}
