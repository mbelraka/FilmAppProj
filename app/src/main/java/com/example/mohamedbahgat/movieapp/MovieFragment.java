package com.example.mohamedbahgat.movieapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by MohamedBahgat on 2016-12-01.
 */

public class MovieFragment extends Fragment {

    public MovieFragment(){

    }

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.movie_info_fragment, container, false);

        Intent intent = this.getActivity().getIntent();

        if(intent != null && intent.getExtras() != null){

            Bundle bundle = intent.getBundleExtra("movie");
            Movie movie = bundle.getParcelable("movie");

            if(movie != null){

                TextView movie_title = (TextView)rootView.findViewById(R.id.movie_info_title);
                movie_title.setText(movie.getTitle());

                ImageView movie_poster = (ImageView)rootView.findViewById(R.id.poster);
                Picasso.with(this.getContext()).load(BuildConfig.imageURL + movie.getPoster_path()).into(movie_poster);

                TextView movie_release_date = (TextView)rootView.findViewById(R.id.release_year);
                movie_release_date.setText(movie.getReleaseDate());

                TextView movie_rating = (TextView)rootView.findViewById(R.id.rating);
                movie_rating.setText(movie.getRatingString());

                TextView movie_overview = (TextView)rootView.findViewById(R.id.overview);
                movie_overview.setText(movie.getOverview());
            }

        }

        return rootView;
    }
}
