package com.isa.analysis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String eComparison (Model model,
                               @RequestParam(value = "id1", required = false, defaultValue = "12")Long id1,
                               @RequestParam(value = "id2", required = false, defaultValue = "34")Long id2
                               )
    {
        //top3研究方向
        
        //top3论文发表数

        //top3论文引用数

        //top5常用合作机构

        return "ComparisonofExpert";
    }

}
