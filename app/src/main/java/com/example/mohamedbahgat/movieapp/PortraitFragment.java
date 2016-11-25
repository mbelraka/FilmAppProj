package com.example.mohamedbahgat.movieapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;

/**
 * Created by MohamedBahgat on 2016-11-25.
 */

public class PortraitFragment extends Fragment {

    ArrayAdapter<String> movieAdapter;

    public PortraitFragment(){

    }


    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        FetchMoviesTask fetchTask = new FetchMoviesTask();
        fetchTask.execute();
    }

}
