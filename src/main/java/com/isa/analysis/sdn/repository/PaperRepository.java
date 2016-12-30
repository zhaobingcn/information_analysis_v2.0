package com.isa.analysis.sdn.repository;

import com.isa.analysis.sdn.entity.Paper;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by zhzy on 2016/12/30.
 */
public interface PaperRepository extends GraphRepository<Paper> {
}
