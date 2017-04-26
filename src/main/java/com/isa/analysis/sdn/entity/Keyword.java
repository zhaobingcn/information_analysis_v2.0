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
@JsonIdentityInfo(generator = JSOGGenerator.class)
@NodeEntity(label = "Keyword")
public class Keyword {

    @GraphId
    private Long id;

    @Property(name = "name")
    private String name;

    @Property(name = "partition")
    private Long partition;

    @Relationship(type = "involve", direction = Relationship.OUTGOING)
    private List<Paper> papers;

    public Long getPartition() {
        return partition;
    }

    public void setPartition(Long partition) {
        this.partition = partition;
    }

    @Relationship(type = "similar", direction = Relationship.UNDIRECTED)
    private List<Similar> silimars;



    public String getName() {
        return name;
    }

    public List<Similar> getSilimars() {
        return silimars;
    }

    public void setSilimars(List<Similar> silimars) {
        this.silimars = silimars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Paper> getPapers() {
        return papers;
    }

    public void setPapers(List<Paper> papers) {
        this.papers = papers;
    }
}
