package com.isa.analysis.sdn.entity.QueryResult;

import com.isa.analysis.sdn.entity.Institution;
import org.springframework.data.neo4j.annotation.QueryResult;

/**
 * Created by hexu on 2017/1/6.
 */
@QueryResult
public class InstitutionAndCooperateTimes{
    private Institution ins;
    private Long times;
}