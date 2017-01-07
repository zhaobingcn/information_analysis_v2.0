package com.isa.analysis.sdn.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.*;

/**
 * Created by zhzy on 2017/1/2.
 */
@JsonIdentityInfo(generator = JSOGGenerator.class)
@RelationshipEntity(type = "work_together")
public class WorkTogether {

    @GraphId
    private Long id;

    @Property(name = "weight")
    private Long weight;

    @StartNode
    private Author author1;

    @EndNode
    private Author author2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Author getAuthor1() {
        return author1;
    }

    public void setAuthor1(Author author1) {
        this.author1 = author1;
    }

    public Author getAuthor2() {
        return author2;
    }

    public void setAuthor2(Author author2) {
        this.author2 = author2;
    }
}
