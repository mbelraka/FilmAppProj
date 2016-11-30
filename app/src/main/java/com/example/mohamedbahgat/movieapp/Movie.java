package com.example.mohamedbahgat.movieapp;

/**
 * Created by MohamedBahgat on 2016-11-29.
 */

public class Movie{

    private String title;
    private String poster_path;
    private String overview;
    private String releaseDate;
    private double popularity;
    private double rating;


    public Movie(String title, String poster_path, String overview, String releaseDate, double popularity, double rating){

        this.title = title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.releaseDate = releaseDate;
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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
