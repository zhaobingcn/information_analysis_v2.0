package com.isa.analysis.sdn.entity;

import org.neo4j.ogm.annotation.*;

/**
 * @author zhaobing
 */
@RelationshipEntity(type = "works_in")
public class WorksIn {
    @GraphId
    private Long id;
    @Property
    private long weight;
    @StartNode
    private Author author;
    @EndNode
    private Institution institution;

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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }
}
