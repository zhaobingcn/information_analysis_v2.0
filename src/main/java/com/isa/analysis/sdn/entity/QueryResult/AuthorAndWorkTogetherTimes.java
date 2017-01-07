package com.isa.analysis.sdn.entity.QueryResult;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.isa.analysis.sdn.entity.Author;
import com.isa.analysis.sdn.entity.WorkTogether;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.springframework.data.neo4j.annotation.QueryResult;

/**
 * Created by zhzy on 2017/1/7.
 */
@QueryResult
public class AuthorAndWorkTogetherTimes {
    Author author;
    Long times;
}