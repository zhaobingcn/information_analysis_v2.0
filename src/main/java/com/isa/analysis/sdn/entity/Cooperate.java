package com.isa.analysis.sdn.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.*;

/**
 * Created by zhzy on 2017/1/2.
 */
//@JsonIdentityInfo(generator = JSOGGenerator.class)
@RelationshipEntity(type = "cooperate")
public class Cooperate {

    @GraphId
    private Long id;

    @Property(name = "weight")
    private long weight;

    @StartNode
    private Institution institution1;

    @EndNode
    private Institution institution2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public Institution getInstitution1() {
        return institution1;
    }

    public void setInstitution1(Institution institution1) {
        this.institution1 = institution1;
    }

    public Institution getInstitution2() {
        return institution2;
    }

    public void setInstitution2(Institution institution2) {
        this.institution2 = institution2;
    }
}
