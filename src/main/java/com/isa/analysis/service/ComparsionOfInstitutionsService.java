package com.isa.analysis.service;

import com.isa.analysis.sdn.entity.QueryResult.KeywordAndInvolveTimes;

import java.util.List;
import java.util.Map;

/**
 * Created by Sgc on 2017/4/25 0025.
 */
public interface ComparsionOfInstitutionsService {
    /**
     * 该科研机构关键词引用次数
     * @param limit
     * @param id 科研机构的id
     * @return 该科研机构每个引用的关键词及对应引用次数
     */
    List<KeywordAndInvolveTimes> generateInstitutionKeywordAndInvolveTimes(Long id, int limit);

    /**
     * 该科研机构的名字
     * @param id 科研机构的id
     */
    String generateInstitutionName(Long id);

    Map<String, Integer> generateInstitutionPublishedPapers(Long id, int limit);

}
