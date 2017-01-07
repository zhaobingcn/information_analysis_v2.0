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

    public Keyword getKeyword() {
        return keyword;
    }

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }

    public Long getTimes() {
        return times;
    }

    public void setTimes(Long times) {
        this.times = times;
    }
}
