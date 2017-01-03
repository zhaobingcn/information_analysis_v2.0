package com.isa.analysis.controller;

import com.isa.analysis.restapi.httprepository.RestApiRepository;
import com.isa.analysis.service.IndexPageService;
import com.isa.analysis.service.RestApiService;
import org.neo4j.ogm.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by hexu on 2016/12/30.
 */
@Controller
public class EDetailController {

    @Autowired
    private RestApiRepository restApiRepository;

    @RequestMapping(value = "/detailOfExpert")
    public String eDetail(){
        return "detailOfExpert";
    }

    @RequestMapping(value = "/detailOfExpert/cooperateOfAuthor")
    public @ResponseBody
    String cooperateRelation(
            @RequestParam(value = "name", required = false, defaultValue = "詹毅")String name,
            @RequestParam(value = "institution", required = false, defaultValue = "电子科技集团36所")String institution,
            @RequestParam(value = "depath", required = false, defaultValue = "1")int depath
    ){
        System.out.println(restApiRepository.generateWorkTogetherGraph(name, institution, depath).toString());

        return restApiRepository.generateWorkTogetherGraph(name, institution, depath).toString();
    }
}
