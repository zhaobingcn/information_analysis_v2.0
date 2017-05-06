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
//@NodeEntity(label = "Paper")
public class Paper {

    @GraphId
    private Long id;
    @Property(name = "title")
    private String title;
    @Property(name = "link")
    private String link;
    @Property(name = "quote")
    private int quote;
    @Property(name = "date")
    private String date;

    @Relationship(type = "publish", direction = Relationship.INCOMING)
    private List<Author> authors;

    @Relationship(type = "involve", direction = Relationship.OUTGOING)
    private List<Involve> involves;

    @Relationship(type = "included_in", direction = Relationship.OUTGOING)
    private List<IncludedIn> includedIns;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Involve> getInvolves() {
        return involves;
    }

    public void setInvolves(List<Involve> involves) {
        this.involves = involves;
    }

    public List<IncludedIn> getIncludedIns() {
        return includedIns;
    }

    public void setIncludedIns(List<IncludedIn> includedIns) {
        this.includedIns = includedIns;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getQuote() {
        return quote;
    }

    public void setQuote(int quote) {
        this.quote = quote;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

}
