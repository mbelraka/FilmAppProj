package com.example.mohamedbahgat.movieapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mohamedbahgat.movieapp.BuildConfig;
import com.example.mohamedbahgat.movieapp.Fragments.MovieFragment;
import com.example.mohamedbahgat.movieapp.R;

/**
 * Created by MohamedBahgat on 2016-12-01.
 */

public class MovieActivity extends AppCompatActivity {

    final String LOG_TAG = "MovieActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        setTitle(BuildConfig.BaseTitle + " - movie info");

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.movie_info_container, new MovieFragment()).commit();
        }
    }
}
