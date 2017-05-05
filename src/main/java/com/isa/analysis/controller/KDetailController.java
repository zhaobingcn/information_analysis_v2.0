package com.isa.analysis.controller;

import com.isa.analysis.sdn.entity.Author;
import com.isa.analysis.sdn.entity.Institution;
import com.isa.analysis.sdn.entity.Keyword;
import com.isa.analysis.sdn.entity.Paper;
import com.isa.analysis.sdn.repository.AuthorRepository;
import com.isa.analysis.sdn.repository.InstitutionRepository;
import com.isa.analysis.sdn.repository.KeywordRepository;
import com.isa.analysis.sdn.repository.PaperRepository;
import com.isa.analysis.service.KeywordDetailService;
import com.isa.analysis.service.RestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhzy on 17-4-30.
 */
@Controller
public class KDetailController {

    @Autowired
    private RestApiService restApiService;

    @Autowired
    private KeywordDetailService keywordDetailService;

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @RequestMapping(value = "/keywordDetail")
    public String keywordDetail(@RequestParam(name = "name", required = false, defaultValue = "Turbo码")String name, Model model){

        Keyword keyword = keywordRepository.findByName(name);
        Long id = keyword.getId();
//        Keyword keyword = keywordRepository.findOne(id);

        List<Paper> papers = paperRepository.getTopPapersByKeywordId(id, 8);
        List<Institution> institutions = institutionRepository.getTopInstitutionByKeywordId(id, 8);
        List<Author> authors = authorRepository.getTopAuthorsByKeywordsId(id, 8);

        int institutionsLength = institutions.size();
        int authorsLength = authors.size();
        List<Institution> institutions1 = new ArrayList<>();
        List<Institution> institutions2 = new ArrayList<>();

        List<Author> authors1 = new ArrayList<>();
        List<Author> authors2 = new ArrayList<>();

        if(authorsLength > 4){
            for(int i=0; i<4; i++){
                authors1.add(authors.get(i));
            }
            for(int i=4; i<authorsLength; i++){
                authors2.add(authors.get(i));
            }
        }else{
            for(int i=0; i<authorsLength; i++){
                authors1.add(authors.get(i));
            }
        }

        if(institutionsLength > 4){
            for(int i=0; i<4; i++){
                institutions1.add(institutions.get(i));
            }
            for(int i=4; i<institutionsLength; i++){
                institutions2.add(institutions.get(i));
            }
        }else{
            for(int i=0; i<institutionsLength; i++){
                institutions1.add(institutions.get(i));
            }
        }
        model.addAttribute("authors1", authors1);
        model.addAttribute("institutions1", institutions1);
        model.addAttribute("authors2", authors2);
        model.addAttribute("institutions2", institutions2);
        model.addAttribute("papers", papers);
        model.addAttribute("keyword", keyword);
        return "keyword";
    }

    @RequestMapping(value = "/keywordDetail/keywordsRelationship")
    public @ResponseBody Map<String, Object> keywordsRelationship(@RequestParam(name = "id", required = false, defaultValue = "45")Long id,
                                             @RequestParam(name = "depath", required = false, defaultValue = "2")int depath){
        return restApiService.generateSimilarGraph(id, depath);
    }

    @RequestMapping(value = "/keywordDetail/keywordTrend")
    public @ResponseBody Map<String, Object> keywordTrend(@RequestParam(name = "id", required = false, defaultValue = "48")Long id){
        return keywordDetailService.generateKeywordsTrend(id);
    }


}
