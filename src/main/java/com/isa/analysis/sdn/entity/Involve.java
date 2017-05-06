package com.isa.analysis.sdn.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.*;

/**
 * Created by zhzy on 2017/1/2.
 */
//@JsonIdentityInfo(generator = JSOGGenerator.class)
@RelationshipEntity(type = "involve")
public class Involve {

    @GraphId
    private Long id;

    @Property(name = "weight")
    private long weight;

    @StartNode
    private Paper paper;

    @EndNode
    private Keyword keyword;

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

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public Keyword getKeyword() {
        return keyword;
    }

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }




}
