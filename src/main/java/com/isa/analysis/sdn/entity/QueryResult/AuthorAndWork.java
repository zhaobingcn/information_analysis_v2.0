package com.isa.analysis.sdn.entity.QueryResult;

import com.isa.analysis.sdn.entity.Author;
import com.isa.analysis.sdn.entity.WorkTogether;
import org.springframework.data.neo4j.annotation.QueryResult;

/**
 * Created by zhzy on 2017/1/7.
 */
@QueryResult
public class AuthorAndWork{
    private Author author;
    private WorkTogether workTogether;
}