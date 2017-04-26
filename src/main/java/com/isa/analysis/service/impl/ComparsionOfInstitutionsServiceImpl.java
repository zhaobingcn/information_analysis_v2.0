package com.isa.analysis.service.impl;

import com.isa.analysis.sdn.entity.Paper;
import com.isa.analysis.sdn.entity.QueryResult.KeywordAndInvolveTimes;
import com.isa.analysis.sdn.repository.InstitutionRepository;
import com.isa.analysis.sdn.repository.KeywordRepository;
import com.isa.analysis.service.ComparsionOfInstitutionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Sgc on 2017/4/25 0025.
 */
@Service
public class ComparsionOfInstitutionsServiceImpl implements ComparsionOfInstitutionsService {

    @Autowired
    private KeywordRepository keywordRepository;
    @Autowired
    private InstitutionRepository institutionRepository;


    @Override
    public List<KeywordAndInvolveTimes> generateInstitutionKeywordAndInvolveTimes(Long id, int limit) {
        return keywordRepository.getKeyWordTimesOfInstitutionByInstitutionId(id,limit);
    }

    @Override
    public String generateInstitutionName(Long id) {
        return institutionRepository.getInstituionNameByInstitutionId(id);
    }

    @Override
    public Map<String, Integer> generateInstitutionPublishedPapers(Long id, int limit) {
        List<Paper> papersOfInstitution = new ArrayList<>();
        papersOfInstitution = institutionRepository.getPaperOfInstitutionByInstitutionId(id,limit);
        Map<String, Integer> papersNumOnDate = new TreeMap<>();
        for(int i = 2006;i <= 2016;i++){
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
        Map<String, Integer> resultMap = sortMapByKey(papersNumOnDate);
        return resultMap;
    }
    public static Map<String, Integer> sortMapByKey(Map<String, Integer> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, Integer> sortMap = new TreeMap<String, Integer>(
                new MapKeyComparator());

        sortMap.putAll(map);

        return sortMap;
    }
}
class MapKeyComparator implements Comparator<String>{

    @Override
    public int compare(String str1, String str2) {

        return str1.compareTo(str2);
    }
}