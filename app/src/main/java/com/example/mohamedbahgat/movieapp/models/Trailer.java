package com.example.mohamedbahgat.movieapp.models;

/**
 * Created by MohamedBahgat on 2016-12-02.
 */

public class Trailer {

    private String name;
    private String link;

    public Trailer(String name, String link){
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
