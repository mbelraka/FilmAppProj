package com.example.mohamedbahgat.movieapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.example.mohamedbahgat.movieapp.BuildConfig;
import com.example.mohamedbahgat.movieapp.Fragments.PortraitFragment;
import com.example.mohamedbahgat.movieapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(BuildConfig.BaseTitle);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.activity_main, new PortraitFragment()).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
