package com.isa.analysis.sdn.entity.QueryResult;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.isa.analysis.sdn.entity.Institution;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.springframework.data.neo4j.annotation.QueryResult;

/**
 * Created by hexu on 2017/1/6.
 */
@QueryResult
public class InstitutionAndCooperateTimes{
    Institution ins;
    Long times;

    public Institution getInstitution() {
        return ins;
    }

    public void setInstitution(Institution ins) {
        this.ins = ins;
    }

    public Long getTimes() {
        return times;
    }

    public void setTimes(Long times) {
        this.times = times;
    }
}