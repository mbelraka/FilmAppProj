package com.example.mohamedbahgat.movieapp;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by MohamedBahgat on 2016-11-25.
 */

public class PortraitFragment extends Fragment {

    private MovieAdapter movieAdapter;
    private GridView gridView;

    public PortraitFragment(){

    }


    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.main_fragment, container, false);
        gridView = (GridView) rootView.findViewById(R.id.gridView);

        movieAdapter = new MovieAdapter(this.getContext());

        FetchMoviesTask fetchTask = new FetchMoviesTask();
        fetchTask.execute("popularity", "1");

        gridView.setAdapter(movieAdapter);

        return rootView;
    }

    public class FetchMoviesTask extends AsyncTask<String, Void , List<Movie>> {

        @Override
        protected List<Movie> doInBackground(String... strings) {

            try{

                HttpURLConnection connection = null;

                BufferedReader reader = null;

                String url_path = generateURL(strings[0], Integer.parseInt(strings[1]));

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

                List<Movie> data = getDataFromJSON(stringBuffer.toString());

                return data;

            } catch(Exception e){

                System.out.println("exception" + e.getMessage());

            }

            return null;
        }

        protected List<Movie> getDataFromJSON(String jsonString){

            try{

                JSONObject jsonObject = new JSONObject(jsonString);
                int pageNum = jsonObject.getInt("page");
                JSONArray itemsArray = jsonObject.getJSONArray("results");
                List<Movie> movies = new ArrayList<Movie>();

                for(int i = 0; i < itemsArray.length(); i++){

                    JSONObject movieJson = itemsArray.getJSONObject(i);
                    String title = movieJson.getString("original_title");
                    String poster_path = movieJson.getString("poster_path");
                    String overview = movieJson.getString("overview");
                    String releaseDate = movieJson.getString("release_date");
                    double popularity = movieJson.getDouble("popularity");
                    double rating = movieJson.getDouble("vote_average");

                    Movie movie = new Movie(title, poster_path, overview, releaseDate, popularity, rating);

                    movies.add(movie);
                }

                return movies;

            } catch (Exception e){

                System.out.println("exception" + e.getMessage());
            }


            return null;
        }

        protected String generateURL(String param, int pageNum){

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

        @Override
        protected void onPostExecute(List<Movie> movies) {

            movieAdapter.setMovies(movies);
            movieAdapter.notifyDataSetChanged();
        }
    }

}
