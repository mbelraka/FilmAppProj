package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mohamedbahgat.movieapp.models.Movie;
import com.example.mohamedbahgat.movieapp.models.Trailer;
import com.example.mohamedbahgat.movieapp.models.Review;

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



    public MovieDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.beginTransaction();
        sqLiteDatabase.execSQL(MOVIE_TABLE_QUERY);
        sqLiteDatabase.execSQL(TRAILER_TABLE_QUERY);
        sqLiteDatabase.execSQL(REVIEW_TABLE_QUERY);
        sqLiteDatabase.endTransaction();
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

        String insert_query = "INSERT INTO Favourite_Movies(id, title, poster_path, overview, release_date, popularity, rating) VALUES (" +
                movie.getId() + ", " + movie.getTitle() + ", " + movie.getPoster_path() + ", " + movie.getOverview() + ", "
                + movie.getReleaseDate() + ", " + movie.getPopularity() + ", " + movie.getRating() + ");";

        SQLiteDatabase db = this.getWritableDatabase();

        db.beginTransaction();
        db.execSQL(insert_query);
        db.endTransaction();

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

        String insert_query = "INSERT INTO Trailers(movie_id, name, link) VALUES (" +
                movie_id + ", " + trailer.getName() + ", " + trailer.getLink() + ");";

        SQLiteDatabase db = this.getWritableDatabase();

        db.beginTransaction();
        db.execSQL(insert_query);
        db.endTransaction();
    }

    public void insertReview(String movie_id, Review review){

        String insert_query = "INSERT INTO Trailers(movie_id, link, author, content) VALUES (" +
                movie_id + ", " + review.getLink() + ", " + review.getAuthor() + ", " + review.getContent() +  ");";

        SQLiteDatabase db = this.getWritableDatabase();

        db.beginTransaction();
        db.execSQL(insert_query);
        db.endTransaction();
    }
}
