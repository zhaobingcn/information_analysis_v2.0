package com.isa.analysis.sdn.entity.QueryResult;

import com.isa.analysis.sdn.entity.Author;

/**
 * Created by zhzy on 2017/10/15.
 */
public class AuthorAndScore {

    Author author;
    double score;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
