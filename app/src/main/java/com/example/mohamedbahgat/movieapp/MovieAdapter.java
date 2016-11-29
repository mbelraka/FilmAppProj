package com.example.mohamedbahgat.movieapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by MohamedBahgat on 2016-11-28.
 */

public class MovieAdapter extends BaseAdapter {

    private Context context;
    private Movie[] movies;

    public MovieAdapter(Context context){
        this.setContext(context);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Movie movie = movies[i];

        ImageView movie_poster = (ImageView)view.findViewById(R.id.movie_poster);
        TextView movie_title = (TextView)view.findViewById(R.id.movie_title);
        ImageView favourite_btn = (ImageView)view.findViewById(R.id.favourite_btn);

        //movie_poster.setImageResource(movie.get);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Movie[] getMovies() {
        return movies;
    }

    public void setMovies(Movie[] movies) {
        this.movies = movies;
    }

    public class Movie{

        private String poster_path;
        private String title;
        private double popularity;
        private double rating;

        public Movie(String poster_path, String title, double popularity, double rating){

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
}
