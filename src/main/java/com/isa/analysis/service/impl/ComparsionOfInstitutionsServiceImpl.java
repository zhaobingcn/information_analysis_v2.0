package com.isa.analysis.service.impl;

import com.isa.analysis.sdn.entity.QueryResult.KeywordAndInvolveTimes;
import com.isa.analysis.sdn.repository.KeywordRepository;
import com.isa.analysis.service.ComparsionOfInstitutionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sgc on 2017/4/25 0025.
 */
@Service
public class ComparsionOfInstitutionsServiceImpl implements ComparsionOfInstitutionsService {

    @Autowired
    private KeywordRepository keywordRepository;
    @Override
    public List<KeywordAndInvolveTimes> generateInstitutionKeywordAndInvolveTimes(Long id, int limit) {
        return keywordRepository.getKeyWordTimesOfInstitutionByInstitutionId(id,limit);
    }
}
