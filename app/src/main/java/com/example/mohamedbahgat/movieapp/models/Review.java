package com.example.mohamedbahgat.movieapp.models;

/**
 * Created by MohamedBahgat on 2016-12-02.
 */

public class Review {

    private String author;
    private String content;
    private String link;

    public Review(String author, String content, String link){

        this.author = author;
        this.content = content;
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
