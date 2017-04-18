package com.isa.analysis.service.impl;

import com.isa.analysis.sdn.entity.Paper;
import com.isa.analysis.sdn.repository.InstitutionRepository;
import com.isa.analysis.service.InstitutionInformationService;
import org.apache.velocity.util.ArrayListWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weka.core.stopwords.Null;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sgc on 2017/4/18 0018.
 */
@Service
public class InstitutionInformationServiceImpl implements InstitutionInformationService {

    @Autowired
    private InstitutionRepository institutionRepository;


    @Override
    public Map<String, Integer> generateInstitutionPublishedPapers(Long id, int limit) {
        List<Paper> papersOfInstitution = new ArrayList<>();
        papersOfInstitution = institutionRepository.getPaperOfInstitutionByInstitutionId(id,limit);
        Map<String, Integer> papersNumOnDate = new HashMap<>();
        for(int i = 2006;i <= 2017;i++){
            papersNumOnDate.put(""+i,0);
        }
        for (Paper oneOfPaper:
             papersOfInstitution) {
            papersNumOnDate.put(oneOfPaper.getDate(),0);
        }
        return null;
    }
}
