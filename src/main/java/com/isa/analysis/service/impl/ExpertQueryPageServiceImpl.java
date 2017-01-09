package com.isa.analysis.service.impl;

import com.isa.analysis.sdn.entity.Author;
import com.isa.analysis.sdn.repository.AuthorRepository;
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
        List<Author> authors = authorRepository.findByFulltextIndexSearch(name, institution, 18);
        List<Map<String, Object>> authorsResult = new ArrayList<>();
        for(Author author:authors){
            authorsResult.add(
                mapUtil.map(
                    "name", author.getName(),
                        "institution", author.getInstitution(),
                        "papersCount", author.getPublishes().size(),
                        "qouteCount", author.getPublishes().stream().map(publish -> Integer.parseInt(publish.getPaper().getQuote())).reduce(0,Integer::sum)
                )
            );
        }
        return authorsResult;
    }
}
