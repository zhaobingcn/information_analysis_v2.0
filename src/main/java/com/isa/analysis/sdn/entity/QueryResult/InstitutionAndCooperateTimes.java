package com.isa.analysis.sdn.entity.QueryResult;

import com.isa.analysis.sdn.entity.Institution;
import org.springframework.data.neo4j.annotation.QueryResult;

/**
 * Created by hexu on 2017/1/6.
 */
@QueryResult
public class InstitutionAndCooperateTimes implements Comparable{
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

    @Override
    public int compareTo(Object o) {
        InstitutionAndCooperateTimes other = (InstitutionAndCooperateTimes)o;
        Long otherTimes = other.getTimes();
        return this.getTimes().compareTo(otherTimes);
    }
}