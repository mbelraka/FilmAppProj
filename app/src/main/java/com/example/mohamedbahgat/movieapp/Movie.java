package com.example.mohamedbahgat.movieapp;

/**
 * Created by MohamedBahgat on 2016-11-29.
 */

public class Movie{

    private String poster_path;
    private String title;
    private double popularity;
    private double rating;

    public Movie(String title, String poster_path, double popularity, double rating){

        this.title = title;
        this.poster_path = poster_path;
        this.popularity = popularity;
        this.rating = rating;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
