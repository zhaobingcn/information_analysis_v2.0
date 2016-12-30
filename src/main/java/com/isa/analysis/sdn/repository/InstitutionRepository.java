package com.isa.analysis.sdn.repository;

import com.isa.analysis.sdn.entity.Institution;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by zhzy on 2016/12/30.
 */

public interface InstitutionRepository extends GraphRepository<Institution> {
}
