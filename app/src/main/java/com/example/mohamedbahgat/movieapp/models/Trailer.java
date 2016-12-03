package com.example.mohamedbahgat.movieapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by MohamedBahgat on 2016-12-02.
 */

public class Trailer implements Parcelable{

    private String name;
    private String link;

    public Trailer(){

    }

    public Trailer(String name, String link){
        this.name = name;
        this.link = link;
    }

    protected Trailer(Parcel in) {
        name = in.readString();
        link = in.readString();
    }

    public static final Creator<Trailer> CREATOR = new Creator<Trailer>() {
        @Override
        public Trailer createFromParcel(Parcel in) {
            return new Trailer(in);
        }

        @Override
        public Trailer[] newArray(int size) {
            return new Trailer[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        parcel.writeString(name);
        parcel.writeString(link);
    }
}
