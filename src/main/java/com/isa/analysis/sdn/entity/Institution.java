package com.isa.analysis.sdn.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * Created by zhzy on 2016/12/30.
 */
//@JsonIdentityInfo(generator = JSOGGenerator.class)
@NodeEntity(label = "Institution")
public class Institution {
    @GraphId
    private Long id;

    @Property(name = "name")
    private String name;

    @Property(name = "location")
    private String location;

    @Relationship(type = "works_in", direction = Relationship.INCOMING)
    private List<Author> authors;

    @Relationship(type = "cooperate", direction = Relationship.UNDIRECTED)
    private List<Cooperate> cooperates;

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
