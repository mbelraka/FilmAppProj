package com.example.mohamedbahgat.movieapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.mohamedbahgat.movieapp.BuildConfig;
import com.example.mohamedbahgat.movieapp.Fragments.MovieFragment;
import com.example.mohamedbahgat.movieapp.Fragments.PortraitFragment;
import com.example.mohamedbahgat.movieapp.R;
import com.example.mohamedbahgat.movieapp.models.Movie;

import db.MovieDBHelper;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "MainActivity";

    public boolean tabletMode;
    public boolean online;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(BuildConfig.BaseTitle);

        checkConnectivity();

        if(online){
            OpenDB();
            setContentView(R.layout.activity_main);

            if(savedInstanceState == null)
                getSupportFragmentManager().beginTransaction().add(R.id.activity_main, new PortraitFragment()).commit();

            checkMode();
        }
        else{

            setContentView(R.layout.empty_view_layout);
            Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public void checkConnectivity(){

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        online = (networkInfo != null) && (networkInfo.isConnectedOrConnecting());
    }

    public void OpenDB() {

        try{
            new MovieDBHelper(this).getWritableDatabase();
        }
        catch (Exception e){

            Log.e(LOG_TAG, ": OpenDB - ", e);
        }
    }

    public void checkMode() {
        FrameLayout movie_info_frame = (FrameLayout) findViewById(R.id.movie_info_frame);
        tabletMode =  movie_info_frame != null;
    }

    public void LaunchMovie(Movie movie){

        if(tabletMode){

            MovieFragment movieFragment = new MovieFragment();
            movieFragment.setMovie(movie);
            getSupportFragmentManager().beginTransaction().replace(R.id.movie_info_frame, movieFragment).commit();

        }
        else {

            try{
                Intent intent = new Intent(this, MovieActivity.class);
                intent.putExtra(Intent.EXTRA_TITLE, movie.getTitle());
                Bundle bundle = new Bundle();
                bundle.putParcelable("movie", movie);
                intent.putExtra("movie", bundle);
                intent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                startActivity(intent);
            }catch (Exception e){

                Log.e(LOG_TAG, ": LaunchMovie - ", e);
            }
        }
    }
}
