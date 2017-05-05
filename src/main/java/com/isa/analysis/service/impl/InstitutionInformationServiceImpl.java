package com.isa.analysis.service.impl;

import com.isa.analysis.sdn.entity.Institution;
import com.isa.analysis.sdn.entity.Paper;
import com.isa.analysis.sdn.entity.QueryResult.InstitutionAndCooperateTimes;
import com.isa.analysis.sdn.entity.QueryResult.KeywordAndInvolveTimes;
import com.isa.analysis.sdn.repository.InstitutionRepository;
import com.isa.analysis.sdn.repository.KeywordRepository;
import com.isa.analysis.sdn.repository.PaperRepository;
import com.isa.analysis.service.InstitutionInformationService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.velocity.util.ArrayListWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weka.core.stopwords.Null;

import java.util.*;

/**
 * Created by Sgc on 2017/4/18 0018.
 */
@Service
public class InstitutionInformationServiceImpl implements InstitutionInformationService {

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private PaperRepository paperRepository;

    @Override
    public String generateInstitutionName(Long id) {
        return institutionRepository.getInstituionNameByInstitutionId(id);
    }

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
    public Map<String,Integer> generateInstitutionPapersAndQuote(Long id) {
        List<Paper> paperList = paperRepository.getPaperOfInstitutionByInstitutionId(id);
        int numOfPaper = 0;
        int numOfPaperQuote = 0;
        for (Paper paper : paperList
             ) {
            numOfPaper++;
            numOfPaperQuote = numOfPaperQuote + paper.getQuote();
        }
        Map<String,Integer> result = new HashMap<>();
        result.put("numOfPaper",numOfPaper);
        result.put("numOfPaperQuote",numOfPaperQuote);
        return result;
    }

    @Override
    public List<KeywordAndInvolveTimes> generateInstitutionKeywordAndInvolveTimes(Long id, int limit) {

        return keywordRepository.getKeyWordTimesOfInstitutionByInstitutionId(id,limit);
    }

    /*
    * 返回本机构所在地和合作机构以及合作次数
    * */
    @Override
    public List<Object> generateInstitutionAndCooperateTimes(Long id, int limit) {
        List<InstitutionAndCooperateTimes> cooperateInstitutionAndTimes = institutionRepository.getCooperateInstitutionAndCooperateTimesByInstitutionId(id,limit);
        String institutionLocation = institutionRepository.getInstitutionLocationByInstitutionId(id);
        Institution thisInstitution = institutionRepository.getInstitutionByInstitutionId(id);
        List<Object> result = new ArrayList<>();
        result.add(thisInstitution);
        for (InstitutionAndCooperateTimes oneOfRecord : cooperateInstitutionAndTimes
             ) {
            if(oneOfRecord.getInstitution().getLocation()!=null&&!(oneOfRecord.getInstitution().getLocation().equals(""))){
                result.add(oneOfRecord);
            }
        }
        return result;
    }


    /*
    * 返回合作机构以及合作次数
    * */
    @Override
    public List<InstitutionAndCooperateTimes> generateInstitutionCooperateTimes(Long id, int limit) {
        List<InstitutionAndCooperateTimes> cooperateInstitutionAndTimes = institutionRepository.getCooperateInstitutionAndCooperateTimesByInstitutionId(id,limit);
        Collections.sort(cooperateInstitutionAndTimes);
        Collections.reverse(cooperateInstitutionAndTimes);
        List<InstitutionAndCooperateTimes> result = new ArrayList<>();
        int count = 0;
        for (InstitutionAndCooperateTimes oneOfRecord : cooperateInstitutionAndTimes
             ) {
            result.add(oneOfRecord);
            count++;
            if(count > 7)
                break;
        }
        return result;
    }

    @Override
    public List<Institution> generateCompeteInstitution(Long id, int limit) {
        return institutionRepository.getCompeteInstitutionByInstitutionId(id,limit);
    }

    @Override
    public List<Institution> generatePotentialCooperateInstitution(Long id, int limit) {
        return institutionRepository.getPotentialCooperateInstitutionByInstitutionId(id,limit);
    }


}
