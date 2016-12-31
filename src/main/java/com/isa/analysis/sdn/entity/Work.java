package com.isa.analysis.sdn.entity;

import org.neo4j.ogm.annotation.*;

/**
 * @author zhaobing
 */
@RelationshipEntity(type = "work_in")
public class Work {
    @GraphId
    private Long id;
    @Property(name = "name")
    private String name;
    @StartNode
    private Author author;
    @EndNode
    private Institution institution;



    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
