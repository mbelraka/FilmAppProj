package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mohamedbahgat.movieapp.models.Movie;
import com.example.mohamedbahgat.movieapp.models.Trailer;
import com.example.mohamedbahgat.movieapp.models.Review;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MohamedBahgat on 2016-12-03.
 */

public class MovieDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "MovieApp.db";

    static final String MOVIE_TABLE_QUERY = "CREATE TABLE Favourite_Movies(id TEXT PRIMARY KEY, " +
            "title TEXT NOT NULL, " + "poster_path TEXT, " + "overview TEXT, " + "release_date TEXT, " +
            "popularity REAL, " + "rating REAL " + ");";

    static final String TRAILER_TABLE_QUERY = "CREATE TABLE Trailers(id INTEGER PRIMARY KEY AUTOINCREMENT, movie_id INTEGER, name TEXT, link TEXT, FOREIGN KEY(movie_id) REFERENCES Favourite_Movies(id));";

    static final String REVIEW_TABLE_QUERY = "CREATE TABLE Reviews(id INTEGER PRIMARY KEY AUTOINCREMENT, movie_id INTEGER, link TEXT, author TEXT, content TEXT,FOREIGN KEY(movie_id) REFERENCES Favourite_Movies(id));";


    static final String MOVIE_TABLE_DROP_QUERY = "DROP TABLE IF EXISTS Favourite_Movies;";

    static final String TRAILER_TABLE_DROP_QUERY = "DROP TABLE IF EXISTS Trailers;";

    static final String REVIEW_TABLE_DROP_QUERY = "DROP TABLE IF EXISTS Reviews;";



    public MovieDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.beginTransaction();
        sqLiteDatabase.execSQL(MOVIE_TABLE_QUERY);
        sqLiteDatabase.execSQL(TRAILER_TABLE_QUERY);
        sqLiteDatabase.execSQL(REVIEW_TABLE_QUERY);
        sqLiteDatabase.endTransaction();
        sqLiteDatabase.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.beginTransaction();
        sqLiteDatabase.execSQL(MOVIE_TABLE_DROP_QUERY);
        sqLiteDatabase.execSQL(TRAILER_TABLE_DROP_QUERY);
        sqLiteDatabase.execSQL(REVIEW_TABLE_DROP_QUERY);
        sqLiteDatabase.endTransaction();

        onCreate(sqLiteDatabase);

    }

    public void insertMovie(Movie movie){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("id", movie.getId());
        contentValues.put("title", movie.getTitle());
        contentValues.put("poster_path", movie.getPoster_path());
        contentValues.put("overview", movie.getOverview());
        contentValues.put("release_date", movie.getReleaseDate());
        contentValues.put("popularity", movie.getPopularity());
        contentValues.put("rating", movie.getRating());

        db.insert("Favourite_Movies", null, contentValues);
        db.close();

        String movie_id = movie.getId();
        List<Trailer> trailers = movie.getTrailers();

        if(trailers != null){
            for(Trailer trailer : trailers){
                insertTrailer(movie_id, trailer);
            }
        }

        List<Review> reviews = movie.getReviews();

        if(reviews != null){
            for(Review review : reviews){
                insertReview(movie_id, review);
            }
        }
    }

    public void insertTrailer(String movie_id, Trailer trailer){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("movie_id", movie_id);
        contentValues.put("name", trailer.getName());
        contentValues.put("link", trailer.getLink());

        db.insert("Trailers", null, contentValues);
        db.close();
    }

    public void insertReview(String movie_id, Review review){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("movie_id", movie_id);
        contentValues.put("link", review.getLink());
        contentValues.put("author", review.getAuthor());
        contentValues.put("content", review.getContent());

        db.insert("Reviews", null, contentValues);
        db.close();
    }


    public List<Movie> getMovies(){

        List<Movie> movies = new ArrayList<Movie>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query("Favourite_Movies",
                new String[]{"id", "title", "poster_path", "overview", "release_date", "popularity", "rating"},
                null, null, null, null, null);

        if(cursor.moveToFirst()){

            do{
                Movie movie = new Movie();
                movie.setId(cursor.getString(cursor.getColumnIndexOrThrow("id")));
                movie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow("title")));
                movie.setPoster_path(cursor.getString(cursor.getColumnIndexOrThrow("poster_path")));
                movie.setOverview(cursor.getString(cursor.getColumnIndexOrThrow("overview")));
                movie.setReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow("release_date")));
                movie.setPopularity(cursor.getFloat(cursor.getColumnIndexOrThrow("popularity")));
                movie.setPopularity(cursor.getFloat(cursor.getColumnIndexOrThrow("rating")));
                movie.setTrailers(getTrailers(movie.getId()));
                movie.setReviews(getReviews(movie.getId()));

                movies.add(movie);
            } while(cursor.moveToNext());
        }

        db.close();
        return movies;
    }

    public List<Trailer> getTrailers(String movie_id){

        List<Trailer> trailers = new ArrayList<Trailer>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query("Trailers",
                new String[]{"name", "link"},
                "movie_id = ?", new String[]{movie_id}, null, null, null);

        if(cursor.moveToFirst()){

            do{
                Trailer trailer = new Trailer();
                trailer.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                trailer.setLink(cursor.getString(cursor.getColumnIndexOrThrow("link")));

                trailers.add(trailer);
            } while(cursor.moveToNext());
        }

        db.close();
        return trailers;
    }

    public List<Review> getReviews(String movie_id){

        List<Review> reviews = new ArrayList<Review>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query("Reviews",
                new String[]{"link", "author", "content"},
                "movie_id = ?", new String[]{movie_id}, null, null, null);

        if(cursor.moveToFirst()){

            do{
                Review review = new Review();
                review.setLink(cursor.getString(cursor.getColumnIndexOrThrow("link")));
                review.setAuthor(cursor.getString(cursor.getColumnIndexOrThrow("author")));
                review.setContent(cursor.getString(cursor.getColumnIndexOrThrow("content")));

                reviews.add(review);
            } while(cursor.moveToNext());
        }

        db.close();
        return reviews;
    }

    public void deleteMovie(String movie_id){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Favourite_Movies", "id = ?", new String[]{movie_id});
        db.delete("Trailers", "movie_id = ?", new String[]{movie_id});
        db.delete("Reviews", "movie_id = ?", new String[]{movie_id});

        db.close();

    }
}
