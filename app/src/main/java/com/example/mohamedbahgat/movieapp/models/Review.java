package com.example.mohamedbahgat.movieapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by MohamedBahgat on 2016-12-02.
 */

public class Review  implements Parcelable{

    private String author;
    private String content;
    private String link;

    public Review(String author, String content, String link){

        this.author = author;
        this.content = content;
        this.link = link;
    }

    protected Review(Parcel in) {
        author = in.readString();
        content = in.readString();
        link = in.readString();
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(author);
        parcel.writeString(content);
        parcel.writeString(link);
    }
}
