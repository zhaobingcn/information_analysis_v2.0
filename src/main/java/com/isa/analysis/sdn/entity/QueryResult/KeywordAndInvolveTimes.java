package com.isa.analysis.sdn.entity.QueryResult;

import com.isa.analysis.sdn.entity.Keyword;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryResult;

/**
 * Created by zhzy on 2017/1/7.
 */
@QueryResult
public class KeywordAndInvolveTimes {

    Keyword keyword;
    Long times;
}
