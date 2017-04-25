package com.isa.analysis.service;

import com.isa.analysis.sdn.entity.Institution;
import com.isa.analysis.sdn.entity.Paper;
import com.isa.analysis.sdn.entity.QueryResult.InstitutionAndCooperateTimes;
import com.isa.analysis.sdn.entity.QueryResult.KeywordAndInvolveTimes;

import java.util.List;
import java.util.Map;

/**
 * Created by Sgc on 2017/4/18 0018.
 */
public interface InstitutionInformationService {

    /**
     * 该科研机构的名字
     * @param id 科研机构的id
     */
    String generateInstitutionName(Long id);

    /**
     * 该科研机构发表的论文数量
     * @param limit
     * @param id 科研机构的id
     * @return 该科研机构每个年份对应的论文发表量
     */
    Map<String,Integer> generateInstitutionPublishedPapers(Long id, int limit);
    /**
     * 该科研机构发表的论文数量和引用次数
     * @param id 科研机构的id
     * @return 该科研机构发表的论文数量和引用次数
     */
    Map<String,Integer> generateInstitutionPapersAndQuote(Long id);

    /**
     * 该科研机构关键词引用次数
     * @param limit
     * @param id 科研机构的id
     * @return 该科研机构每个引用的关键词及对应引用次数
     */
    List<KeywordAndInvolveTimes> generateInstitutionKeywordAndInvolveTimes(Long id,int limit);
    /**
     * 该科研机构关键词引用次数
     * @param limit
     * @param id 科研机构的id
     * @return 该科研机构每个引用的关键词及对应引用次数
     */
    List<Object> generateInstitutionAndCooperateTimes(Long id,int limit);
    List<InstitutionAndCooperateTimes> generateInstitutionCooperateTimes(Long id,int limit);

    /**
     * 该科研机构的竞争机构
     * @param limit
     * @param id 科研机构的id
     * @return 该科研机构的竞争机构
     */
    List<Institution> generateCompeteInstitution(Long id,int limit);

    /**
     * 该科研机构的潜在合作机构
     * @param limit
     * @param id 科研机构的id
     * @return 该科研机构的潜在合作机构
     */
    List<Institution> generatePotentialCooperateInstitution(Long id,int limit);
}
