package com.isa.analysis.controller;

import com.isa.analysis.sdn.entity.Author;
import com.isa.analysis.sdn.entity.Paper;
import com.isa.analysis.sdn.entity.WorkTogether;
import com.isa.analysis.sdn.repository.AuthorRepository;
import com.isa.analysis.sdn.repository.PaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by hexu on 2017/1/6.
 */
@RestController
public class TestController {

    @Autowired
    private PaperRepository paperRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @RequestMapping(value = "/test")
    public Collection<Paper> test(){
        return paperRepository.findByAuthor("詹毅", "电子科技集团36所");
    }

    @RequestMapping(value = "/test1")
    List<Map<Author, WorkTogether>> test1(){
        return authorRepository.getWorkTogetherAuthorsByAuthor("詹毅", "电子科技集团36所");
    }
}
