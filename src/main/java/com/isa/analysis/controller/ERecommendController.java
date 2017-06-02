package com.isa.analysis.controller;

import com.isa.analysis.sdn.entity.Author;
import com.isa.analysis.sdn.entity.QueryResult.AuthorAndWorkTogetherTimes;
import com.isa.analysis.sdn.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by hexu on 2016/12/30.
 */
@Controller
public class ERecommendController {

    @Autowired
    private AuthorRepository authorRepository;

    @RequestMapping(value = "/recommendOfExpert")
    public String expertRecommend(){
        return "recommendOfExpert";
    }

    @RequestMapping(value = "/recommendOfExpert/getTopExpertsByPageRank")
    public @ResponseBody List<AuthorAndWorkTogetherTimes> getTopExpertsByPageRank(@RequestParam(value = "keyword1", required = false)String keyword1,
                                                                                  @RequestParam(value = "keyword2", required = false)String keyword2,
                                                                                  @RequestParam(value = "keyword3", required = false)String keyword3,
                                                                                  @RequestParam(value = "keyword4", required = false)String keyword4,
                                                                                  @RequestParam(value = "iname1", required = false)String iname1,
                                                                                  @RequestParam(value = "iname2", required = false)String iname2
    ){

        return authorRepository.getTopAuthorsByKeyowrdsPageRank(keyword1, keyword2, keyword3, keyword4, iname1, iname2);
    }

    @RequestMapping(value = "/recommendOfExpert/getTopExpertsByAchievement")
    public @ResponseBody List<AuthorAndWorkTogetherTimes> getTopExpertsByAchievement(@RequestParam(value = "keyword1", required = false)String keyword1,
                                                    @RequestParam(value = "keyword2", required = false)String keyword2,
                                                    @RequestParam(value = "keyword3", required = false)String keyword3,
                                                    @RequestParam(value = "keyword4", required = false)String keyword4,
                                                    @RequestParam(value = "iname1", required = false)String iname1,
                                                    @RequestParam(value = "iname2", required = false)String iname2
    ){

        return authorRepository.getTopAuthorsByKeywordsAchievement(keyword1, keyword2, keyword3, keyword4, iname1, iname2);
    }
}
