package com.isa.analysis.service.impl;

import com.isa.analysis.sdn.entity.Author;
import com.isa.analysis.sdn.repository.AuthorRepository;
import com.isa.analysis.sdn.repository.PaperRepository;
import com.isa.analysis.service.ExpertQueryPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhzy on 2017/1/5.
 */
@Service
public class ExpertQueryPageServiceImpl implements ExpertQueryPageService{

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private MapUtil mapUtil;

    @Override
    public List<Map<String, Object>> generateSearchAuthors(String name, String institution) {
        String queryContext = "";
        if(name != null && institution != null){
            queryContext = "name:(" + name + ") AND " + "institution:(" + institution + ")";
        }else if(name == null){
            queryContext = "institution:(" + institution + ")";
        }else if(institution == null){
            queryContext = "name:(" + name + ")";
        }
        List<Author> authors = authorRepository.findByFulltextIndexSearch("author", queryContext , 18);
        List<Map<String, Object>> authorsResult = new ArrayList<>();
        for(Author author:authors){
            System.out.println(author.getName() + author.getInstitution());
            authorsResult.add(
                mapUtil.map(
                    "name", author.getName(),
                        "institution", author.getInstitution(),
                        "papersCount", paperRepository.getPapersCountByAuthor(author.getName(), author.getInstitution()),
                        "quoteCount", authorRepository.getPapersQuoteCount(author.getName(), author.getInstitution())
                )
            );
        }
        return authorsResult;
    }
}
