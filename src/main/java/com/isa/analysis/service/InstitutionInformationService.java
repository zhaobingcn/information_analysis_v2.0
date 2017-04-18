package com.isa.analysis.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Sgc on 2017/4/18 0018.
 */
public interface InstitutionInformationService {
    /**
     * 该科研机构发表的论文数量
     * @param limit
     * @param id 科研机构的id
     * @return 该科研机构每个年份对应的论文发表量
     */
    Map<String,Integer> generateInstitutionPublishedPapers(Long id, int limit);
}
