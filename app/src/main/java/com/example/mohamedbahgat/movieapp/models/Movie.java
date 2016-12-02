package com.example.mohamedbahgat.movieapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by MohamedBahgat on 2016-11-29.
 */

public class Movie implements Parcelable{

    private String id;
    private String title;
    private String poster_path;
    private String overview;
    private String releaseDate;
    private double popularity;
    private double rating;

    private List<Trailer> trailers;
    private List<Review> reviews;

    public Movie(String id, String title, String poster_path, String overview, String releaseDate, double popularity, double rating){

        this.id = id;
        this.title = title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
        this.rating = rating;
    }

    protected Movie(Parcel in) {
        id = in.readString();
        title = in.readString();
        poster_path = in.readString();
        overview = in.readString();
        releaseDate = in.readString();
        popularity = in.readDouble();
        rating = in.readDouble();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

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

    public String getRatingString(){ return rating + " / 10";}

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(poster_path);
        parcel.writeString(overview);
        parcel.writeString(releaseDate);
        parcel.writeDouble(popularity);
        parcel.writeDouble(rating);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Trailer> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
