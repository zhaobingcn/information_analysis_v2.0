package com.isa.analysis.service.impl;

import com.isa.analysis.sdn.entity.Author;
import com.isa.analysis.sdn.repository.AuthorRepository;
import com.isa.analysis.service.ExpertQueryPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhzy on 2017/1/5.
 */
@Service
public class ExpertQueryPageServiceImpl implements ExpertQueryPageService{

    @Autowired
    private AuthorRepository authorRepository;


    @Override
    public List<Author> generateSearchAuthors(String name, String institution) {
        String queryContext = "";
        if(name != null && institution != null){
            queryContext = "name:(" + name + ") AND " + "institution:(" + institution + ")";
        }else if(name == null){
            queryContext = "institution:(" + institution + ")";
        }else if(institution == null){
            queryContext = "name:(" + name + ")";
        }
        return authorRepository.findByFulltextIndexSearch("author", queryContext, 18);
    }
}
