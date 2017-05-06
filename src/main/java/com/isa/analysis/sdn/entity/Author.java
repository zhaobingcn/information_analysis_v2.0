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
@NodeEntity(label = "Author")
public class Author {
    @GraphId
    private Long id;

    @Property(name = "name")
    private String name;

    @Property(name = "institution")
    private String institution;

    @Relationship(type = "works_in", direction = Relationship.OUTGOING)
    private WorksIn work;

    @Relationship(type = "publish", direction = Relationship.OUTGOING)
    private List<Publish> publishes;

    @Relationship(type = "work_together", direction = Relationship.UNDIRECTED)
    private List<WorkTogether> worksTogethers;

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public List<Publish> getPublishes() {
        return publishes;
    }

    public void setPublishes(List<Publish> publishes) {
        this.publishes = publishes;
    }

    public List<WorkTogether> getWorksTogethers() {
        return worksTogethers;
    }

    public void setWorksTogethers(List<WorkTogether> worksTogethers) {
        this.worksTogethers = worksTogethers;
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

    public WorksIn getWork() {
        return work;
    }

    public void setWork(WorksIn work) {
        this.work = work;
    }


}
