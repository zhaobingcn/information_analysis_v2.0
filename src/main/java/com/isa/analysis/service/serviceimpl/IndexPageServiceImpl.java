package com.isa.analysis.service.serviceimpl;

import com.isa.analysis.sdn.entity.Author;
import com.isa.analysis.sdn.entity.Institution;
import com.isa.analysis.sdn.entity.Keyword;
import com.isa.analysis.sdn.entity.Paper;
import com.isa.analysis.sdn.repository.Neo4jTemplateRepository;
import com.isa.analysis.service.IndexPageService;
import com.isa.analysis.service.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hexu on 2017/1/3.
 */
@Service
public class IndexPageServiceImpl implements IndexPageService {

    @Autowired
    private Neo4jTemplateRepository neo4jTemplateRepository;

    @Autowired
    private MapUtil mapUtil;

    @Override
    public Map<String, Object> generateInfluentialEntitys(String entityName, int limit) {
        Map<String, Long> influentialEntities = neo4jTemplateRepository.getInfluentialEntities(entityName, limit);
        List<Map<String, Object>> dataGroup = new ArrayList<>();
        for(Map.Entry<String, Long> oneOfEntityData:influentialEntities.entrySet()){
            dataGroup.add(mapUtil.map("name", oneOfEntityData.getKey(), "value", oneOfEntityData.getValue()));
        }
        Map<String, Object> finalInfluentialEntityData = new HashMap<>();
        finalInfluentialEntityData.put("data", dataGroup);
        return finalInfluentialEntityData;
    }

    @Override
    public Map<String, Long> generateTotalEntitys() {
        Map<String, Long> totalEntitys = new HashMap<>();
        totalEntitys.put("institutions", neo4jTemplateRepository.getCountOfEntities(Institution.class));
        totalEntitys.put("authors", neo4jTemplateRepository.getCountOfEntities(Author.class));
        totalEntitys.put("papers", neo4jTemplateRepository.getCountOfEntities(Paper.class));
        totalEntitys.put("keywords", neo4jTemplateRepository.getCountOfEntities(Keyword.class));
        return totalEntitys;
    }
}
