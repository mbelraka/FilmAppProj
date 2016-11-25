package com.example.mohamedbahgat.movieapp;

import android.net.Uri;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by MohamedBahgat on 2016-11-25.
 */

public class FetchMoviesTask extends AsyncTask<String, Void , String[]>{

    @Override
    protected String[] doInBackground(String... strings) {

        try{

            HttpURLConnection connection = null;

            BufferedReader reader = null;

            String forecaseJsonStr = null;

            String url_path = generateURL(strings[0]);

            URL url = new URL(url_path);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            InputStream inputStream = connection.getInputStream();

            if(inputStream == null){

                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuffer stringBuffer = new StringBuffer();
            String line;

            while ((line = reader.readLine()) != null){

                stringBuffer.append(line + "\n");
            }

            if(stringBuffer.length() == 0){
                return  null;
            }

            

        } catch(Exception e){

        }

        return new String[0];
    }

    protected String generateURL(String param){

        try{

            Uri builder = Uri.parse(BuildConfig.URL).buildUpon().
                    appendQueryParameter(BuildConfig.SortByParameter,
                            (param.equals("popularity")? BuildConfig.PopularityParameter : BuildConfig.RateParameter)).
                    appendQueryParameter(BuildConfig.APIParameter, BuildConfig.APIKEY).build();

            return builder.toString();

        } catch (Exception e){

        }

        return null;
    }
}
