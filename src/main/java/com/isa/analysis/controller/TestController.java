package com.isa.analysis.controller;

import com.isa.analysis.sdn.entity.*;
import com.isa.analysis.sdn.entity.QueryResult.InstitutionAndCooperateTimes;
import com.isa.analysis.sdn.repository.AuthorRepository;
import com.isa.analysis.sdn.repository.InstitutionRepository;
import com.isa.analysis.sdn.repository.PaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.List;

/**
 * Created by hexu on 2017/1/6.
 */
@Controller
public class TestController {

    @Autowired
    private PaperRepository paperRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private InstitutionRepository institutionRepository;


    @RequestMapping(value = "/test")
    public @ResponseBody Collection<Paper> test(){
        return paperRepository.findByAuthor("詹毅", "电子科技集团36所");
    }

    @RequestMapping(value = "/test1")
    public @ResponseBody String test1(){
        return authorRepository.getWorkTogetherAuthorsByAuthor("詹毅", "电子科技集团36所").toString();
    }

    @RequestMapping(value = "/test2")
    public @ResponseBody String test2(){
        return  institutionRepository.getCooperateInstitutionByAuthor("詹毅", "电子科技集团36所").toString();
    }
}
