package com.isa.analysis.sdn.repository;

import com.isa.analysis.sdn.entity.Keyword;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zhzy on 2016/12/30.
 */
@Repository
public interface KeyowrdRepository extends GraphRepository<Keyword> {

}
