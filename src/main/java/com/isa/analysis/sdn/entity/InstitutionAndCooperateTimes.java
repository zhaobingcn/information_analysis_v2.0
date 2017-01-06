package com.isa.analysis.sdn.entity;

import org.springframework.data.neo4j.annotation.QueryResult;

/**
 * Created by hexu on 2017/1/6.
 */
@QueryResult
public class InstitutionAndCooperateTimes{
    String ins;
    Long times;
}