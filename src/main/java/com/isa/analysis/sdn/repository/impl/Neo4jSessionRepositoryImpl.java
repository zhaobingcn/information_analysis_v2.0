package com.isa.analysis.sdn.repository.impl;

import com.isa.analysis.sdn.repository.Neo4jTemplateRepository;
import org.neo4j.ogm.session.Neo4jSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by zhzy on 2016/12/31.
 */
@Repository
public class Neo4jSessionRepositoryImpl {

    @Autowired
    private Neo4jSession neo4jSession;
}
