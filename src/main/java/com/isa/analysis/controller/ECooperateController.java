package com.isa.analysis.controller;

import com.isa.analysis.sdn.entity.Author;
import com.isa.analysis.sdn.entity.QueryResult.KeywordAndInvolveTimes;
import com.isa.analysis.sdn.repository.AuthorRepository;
import com.isa.analysis.sdn.repository.KeywordRepository;
import com.isa.analysis.sdn.repository.Neo4jTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by hexu on 2016/12/30.
 */
@Controller
public class ECooperateController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private Neo4jTemplateRepository neo4jTemplateRepository;

    @RequestMapping(value = "/ComparisonofExpert/commitComparison")
    public String eComparison (Model model,
                               @RequestParam(value = "id1", required = false, defaultValue = "12")Long id1,
                               @RequestParam(value = "id2", required = false, defaultValue = "34")Long id2
                               )
    {

        Author author1 = authorRepository.findById(id1);
        Author author2 = authorRepository.findById(id2);

        //top3研究方向,论文发表数,论文引用数
        List<Map<String, Object>> authorsComparsionInformations1 = neo4jTemplateRepository.getAuthorsComparsionInformations(id1);
        List<Map<String, Object>> authorsComparsionInformations2 = neo4jTemplateRepository.getAuthorsComparsionInformations(id2);

        //top5常用合作机构

        return "ComparisonofExpert";
    }

}
