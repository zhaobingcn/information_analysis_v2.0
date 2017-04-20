package com.isa.analysis.service.impl;

import com.isa.analysis.sdn.entity.Paper;
import com.isa.analysis.sdn.entity.QueryResult.InstitutionAndCooperateTimes;
import com.isa.analysis.sdn.entity.QueryResult.KeywordAndInvolveTimes;
import com.isa.analysis.sdn.repository.InstitutionRepository;
import com.isa.analysis.sdn.repository.KeywordRepository;
import com.isa.analysis.service.InstitutionInformationService;
import org.apache.commons.collections.map.HashedMap;
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

    @Autowired
    private KeywordRepository keywordRepository;

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
            String date = oneOfPaper.getDate();
            String yearOfDate = date.substring(0,4);
            if(papersNumOnDate.containsKey(yearOfDate)){
                int num = papersNumOnDate.get(yearOfDate);
                papersNumOnDate.put(yearOfDate,num+1);
            }
        }
        return papersNumOnDate;
    }

    @Override
    public List<KeywordAndInvolveTimes> generateInstitutionKeywordAndInvolveTimes(Long id, int limit) {

        return keywordRepository.getKeyWordTimesOfInstitutionByInstitutionId(id,limit);
    }

    @Override
    public List<List<Map<String,Object>>> generateInstitutionAndCooperateTimes(Long id, int limit) {
        List<InstitutionAndCooperateTimes> cooperateInstitutionAndTimes = institutionRepository.getCooperateInstitutionAndCooperateTimesByInstitutionId(id,limit);
        String institutionLocation = institutionRepository.getInstitutionLocationByInstitutionId(id);
        Map<String,Object> location = new HashMap<>();
        location.put("name",institutionLocation);
        List<List<Map<String,Object>>> result = new ArrayList<>();
        for (InstitutionAndCooperateTimes oneOfRecord : cooperateInstitutionAndTimes
             ) {
            List<Map<String,Object>> oneOfResult = new ArrayList<>();
            Map<String,Object> mapOfResult = new HashMap<>();
            mapOfResult.put("name",oneOfRecord.getInstitution().getLocation());
            mapOfResult.put("value",oneOfRecord.getTimes());
            oneOfResult.add(location);
            oneOfResult.add(mapOfResult);
            result.add(oneOfResult);
        }

        return result;
    }


}
