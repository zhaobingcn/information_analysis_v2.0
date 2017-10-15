package com.isa.analysis.service.impl;

import com.isa.analysis.sdn.entity.Author;
import com.isa.analysis.sdn.entity.Institution;
import com.isa.analysis.sdn.entity.Keyword;
import com.isa.analysis.sdn.repository.AuthorRepository;
import com.isa.analysis.sdn.repository.InstitutionRepository;
import com.isa.analysis.sdn.repository.KeywordRepository;
import com.isa.analysis.sdn.repository.PaperRepository;
import com.isa.analysis.service.QueryPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by zhzy on 2017/1/5.
 */
@Service
public class QueryPageServiceImpl implements QueryPageService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private MapUtil mapUtil;

    @Override
//    @Transactional
    public List<Map<String, Object>> generateSearchAuthors(String name, String institution) {
        String queryContext = "";
        if(!name.equals("") && !institution.equals("")){
            queryContext = "name:(" + name + ") AND " + "institution:(" + institution + ")";
        }else if(name.equals("")){
            queryContext = "institution:(" + institution + ")";
        }else if(institution.equals("")){
            queryContext = "name:(" + name + ")";
        }
        List<Author> authors = authorRepository.findByFulltextIndexSearch("author", queryContext , 18);
        List<Map<String, Object>> authorsResult = new ArrayList<>();
        for(Author author:authors){
            authorsResult.add(
                mapUtil.map(
                        "author", author,
                        "id", author.getId(),
                        "papersCount", paperRepository.getPapersCountByAuthorId(author.getId()),
                        "quoteCount", authorRepository.getPapersQuoteCountById(author.getId())
                )
            );
        }

        Collections.sort(authorsResult, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                if((((Author)o1.get("author")).getName()).equals(((Author)o2.get("author")).getName())){
                    return Integer.parseInt(o1.get("papersCount").toString()) - Integer.parseInt(o2.get("papersCount").toString());
                }else {
                    return 0;
                }
            }
        });

        return authorsResult;
    }

    @Override
    public List<Map<String, Object>> generateSearchInstitutions(String name) {
        String queryContext = "";
        if(!name.equals("")){
            queryContext =  "name:(" + name + ")";
        }

        List<Institution> institutions = institutionRepository.findByFulltextIndexSearch("institution", queryContext, 18);
        List<Map<String, Object>> institutionsResult = new ArrayList<>();
        for(Institution institution:institutions){
            institutionsResult.add(
                    mapUtil.map(
                            "institution", institution,
                            "id", institution.getId(),
                            "authorsCount", institutionRepository.getAuthorsCount(institution.getId()),
                            "papersCount", institutionRepository.getPapersCount(institution.getId())
                    )
            );
        }


        return institutionsResult;
    }

    @Override
    public List<Map<String, Object>> generateSearchKeywords(String name) {
        String queryContext = "";
        if(!name.equals("")){
            queryContext =  "name:(" + name + ")";
        }
        List<Keyword> keywords = keywordRepository.findByFulltextIndexSearch("keyword", queryContext, 18);
        List<Map<String, Object>> keywordsResult = new ArrayList<>();
        for(Keyword keyword: keywords){
            keywordsResult.add(
                    mapUtil.map(
                            "keyword", keyword,
                            "id", keyword.getId(),
                            "involvetimes",keywordRepository.getKeywordInvolveTimes(keyword.getId())
                    )
            );
        }
        return keywordsResult;
    }
}
