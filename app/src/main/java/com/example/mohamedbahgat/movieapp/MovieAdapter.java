package com.example.mohamedbahgat.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.mohamedbahgat.movieapp.models.Movie;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by MohamedBahgat on 2016-11-28.
 */

public class MovieAdapter extends BaseAdapter {

    private Context context;
    private List<Movie> movies;

    public MovieAdapter(Context context){

        this.context = context;
    }

    @Override
    public int getCount() {

        if(movies == null){
            return 0;
        }

        return movies.size();
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

        if(view == null){

            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.movie_item_layout, null);
        }

        Movie movie = movies.get(i);

        ImageView movie_poster = (ImageView)view.findViewById(R.id.movie_poster);
        //TextView movie_title = (TextView)view.findViewById(R.id.movie_title);
        //ImageView favourite_btn = (ImageView)view.findViewById(R.id.favourite_btn);
        Picasso.with(context).load(BuildConfig.imageURL + movie.getPoster_path()).into(movie_poster);

        //movie_title.setText(movie.getTitle());

        return view;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

}
