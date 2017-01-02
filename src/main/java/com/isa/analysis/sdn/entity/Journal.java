package com.isa.analysis.sdn.entity;


import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.boot.autoconfigure.reactor.ReactorAutoConfiguration;

import java.util.Collection;

/**
 * Created by zhzy on 2016/12/30.
 */
@NodeEntity(label = "Journal")
public class Journal{

    @GraphId
    private Long id;

    @Property(name = "name")
    private String name;

    @Relationship(type = "included_in", direction = Relationship.INCOMING)
    private Collection<Paper> papers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Paper> getPapers() {
        return papers;
    }

    public void setPapers(Collection<Paper> papers) {
        this.papers = papers;
    }
}
