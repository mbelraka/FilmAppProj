package com.example.mohamedbahgat.movieapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mohamedbahgat.movieapp.BuildConfig;
import com.example.mohamedbahgat.movieapp.R;
import com.example.mohamedbahgat.movieapp.models.Movie;
import com.example.mohamedbahgat.movieapp.models.Review;
import com.example.mohamedbahgat.movieapp.models.Trailer;
import com.squareup.picasso.Picasso;

import java.util.List;

import db.MovieDBHelper;

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

                ImageView movie_favourite_btn = (ImageView)rootView.findViewById(R.id.favourite_btn);
                movie_favourite_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MovieDBHelper mdb = new MovieDBHelper(getContext());
                        mdb.insertMovie(movie);
                    }
                });

                if(movie.getTrailers() != null){

                    ExpandableListView trailers_list = (ExpandableListView) rootView.findViewById(R.id.trailers_list);
                    TrailerAdapter trailerAdapter = new TrailerAdapter(this.getContext());
                    trailers_list.setAdapter(trailerAdapter);
                    trailerAdapter.setTrailers(movie.getTrailers());
                }

                if(movie.getReviews() != null){

                    ListView reviews_list = (ListView) rootView.findViewById(R.id.reviews_list);
                    ReviewAdapter reviewAdapter = new ReviewAdapter(this.getContext());
                    reviews_list.setAdapter(reviewAdapter);
                    reviewAdapter.setReviews(movie.getReviews());
                }

            }

        }

        return rootView;
    }

    public class TrailerAdapter implements ExpandableListAdapter {

        private Context context;
        private List<Trailer> trailers;

        public TrailerAdapter(Context context){

            this.setContext(context);
        }

        @Override
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public int getGroupCount() {
            if(trailers == null){
                return 0;
            }

            return trailers.size();
        }

        @Override
        public int getChildrenCount(int i) {
            return 1;
        }

        @Override
        public Object getGroup(int i) {
            return null;
        }

        @Override
        public Object getChild(int i, int i1) {
            return null;
        }

        @Override
        public long getGroupId(int i) {
            return 0;
        }

        @Override
        public long getChildId(int i, int i1) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

            if(view == null){

                LayoutInflater inflater = LayoutInflater.from(getContext());
                view = inflater.inflate(R.layout.trailer_parent_item, null);
            }

            TextView trailer_title = (TextView) view.findViewById(R.id.trailer_title);
            trailer_title.setText(trailers.get(i).getName());

            return view;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

            if(view == null){

                LayoutInflater inflater = LayoutInflater.from(getContext());
                view = inflater.inflate(R.layout.trailer_child_item, null);
            }

            TextView trailer_link = (TextView) view.findViewById(R.id.trailer_link);
            final String trailer_link_string = trailers.get(i).getLink();
            trailer_link.setText(trailer_link_string);

            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {

                    try{
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(trailer_link_string)));
                    }
                    catch (Exception e){

                    }
                }
            });

            return view;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public void onGroupExpanded(int i) {

        }

        @Override
        public void onGroupCollapsed(int i) {

        }

        @Override
        public long getCombinedChildId(long l, long l1) {
            return 0;
        }

        @Override
        public long getCombinedGroupId(long l) {
            return 0;
        }

        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        public List<Trailer> getTrailers() {
            return trailers;
        }

        public void setTrailers(List<Trailer> trailers) {
            this.trailers = trailers;
        }
    }

    public class ReviewAdapter implements ListAdapter {

        private Context context;
        private List<Review> reviews;

        public ReviewAdapter(Context context){

            this.context = context;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEnabled(int i) {
            return false;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public int getCount() {

            if(reviews == null){
                return 0;
            }

            return reviews.size();
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
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            if(view == null){

                LayoutInflater inflater = LayoutInflater.from(getContext());
                view = inflater.inflate(R.layout.review_item_layout, null);
            }

            TextView review_url_body = (TextView) view.findViewById(R.id.review_url_body);
            final String review_url_string = getReviews().get(i).getLink();
            review_url_body.setText(review_url_string);

            TextView review_author_body = (TextView) view.findViewById(R.id.review_author_body);
            review_author_body.setText(getReviews().get(i).getAuthor());

            TextView review_content_body = (TextView) view.findViewById(R.id.review_content_body);
            review_content_body.setText(getReviews().get(i).getContent());

            review_url_body.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {

                    try{
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(review_url_string)));
                    }
                    catch (Exception e){

                    }
                }
            });

            return view;

        }

        @Override
        public int getItemViewType(int i) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        public List<Review> getReviews() {
            return reviews;
        }

        public void setReviews(List<Review> reviews) {
            this.reviews = reviews;
        }
    }
}
