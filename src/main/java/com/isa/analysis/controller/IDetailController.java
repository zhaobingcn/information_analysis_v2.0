package com.isa.analysis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhzy on 17-3-21.
 */
@Controller
public class IDetailController {
    @RequestMapping(value = "/tables")
    public String tables(){
        return "tables";
    }

    @RequestMapping(value = "/tables/institution")
    public @ResponseBody List<List<String>> influentialExperts(@RequestParam(value = "limit", required = false, defaultValue = "30")int limit){
        List<List<String>> ans = new ArrayList<List<String>>();
        for(int i = 0;i < 3;i++){
            //List<String> an = new ArrayList<String>();
            for(int j = 0;j < 11;j++){
                List<String> an = new ArrayList<String>();
                an.add(""+(2006+j));
                an.add(""+(Math.random()*100));
                if(i == 0){
                    an.add("论文发表量");
                }else if(i == 1){
                    an.add("专利申请量");
                }else if(i == 2){
                    an.add("项目承接量");
                }
                ans.add(an);
            }
        }
        return ans;
    }
    //public String blank(){
    //    return "/tables";
    //}
}
