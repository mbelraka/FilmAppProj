package com.example.mohamedbahgat.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.w3c.dom.Text;

/**
 * Created by MohamedBahgat on 2016-12-01.
 */

public class MovieActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        Intent intent = this.getIntent();

        if(intent != null && intent.hasExtra(Intent.EXTRA_TITLE)){

            String title = intent.getStringExtra(Intent.EXTRA_TITLE);
            setTitle(title);
        }

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.movie_info_container, new MovieFragment()).commit();
        }
    }
}
