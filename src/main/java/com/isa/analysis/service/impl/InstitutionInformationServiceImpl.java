package com.isa.analysis.service.impl;

import com.isa.analysis.sdn.entity.Paper;
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
    public List<Map<String, Object>> generateInstitutionKeywordTimes(Long id, int limit) {
        List<Map<String,Object>> keywordAndTimesList = new ArrayList<>();
        List<KeywordAndInvolveTimes> keywordAndInvolveTimes = institutionRepository.getKeyWordTimesOfInstitutionByInstitutionId(id,limit);
        //Map<String,Integer> keywordAndTimesMap = institutionRepository.getKeyWordTimesOfInstitutionByInstitutionId(id,limit);
        for (KeywordAndInvolveTimes entry : keywordAndInvolveTimes
                ) {
            Map<String,Object> keywordNameAndTimes = new HashMap<>();
            keywordNameAndTimes.put("name",entry.getKeyword().getName());
            keywordNameAndTimes.put("value",entry.getTimes());
            keywordAndTimesList.add(keywordNameAndTimes);
        }

        return keywordAndTimesList;

    }

    @Override
    public List<KeywordAndInvolveTimes> generateInstitutionKeywordAndInvolveTimes(Long id, int limit) {

        return keywordRepository.getKeyWordTimesOfInstitutionByInstitutionId(id,limit);
    }


}
