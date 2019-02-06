package com.example.kunalparte.themoviedbtask.movies.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.kunalparte.themoviedbtask.utils.Consts;

import java.util.List;


@Entity(tableName = Consts.MOVIES_TABLE)
public class Movies implements Parcelable{

    public Movies(){

    }

    /**
     * vote_count : 966
     * id : 450465
     * video : false
     * vote_average : 6.9
     * title : Glass
     * popularity : 360.395
     * poster_path : /gt5KPtwDMeIOPdVfmjYlFw9EetE.jpg
     * original_language : en
     * original_title : Glass
     * genre_ids : [53,9648,18]
     * backdrop_path : /lvjscO8wmpEbIfOEZi92Je8Ktlg.jpg
     * adult : false
     * overview : In a series of escalating encounters, security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.
     * release_date : 2019-01-16
     */
    @PrimaryKey
    private int id;
    private int vote_count;
    private String video;
    private double vote_average;
    private String title;
    private double popularity;
    private String poster_path;
    private String original_language;

    public String getVideo() {
        return video;
    }

    public String getAdult() {
        return adult;
    }

    private String original_title;
    private String backdrop_path;
    private String adult;
    private String overview;
    private String release_date;
    private List<Integer> genre_ids;

    public static final Parcelable.Creator<Movies> CREATOR = new Parcelable.Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel source) {
            return new Movies(source);
        }

        @Override
        public Movies[] newArray(int size) {

            return new Movies[size];
        }
    };

    protected Movies(Parcel source) {
        this.id = source.readInt();
        this.vote_count = source.readInt();
        this.video = source.readString();
        this.vote_average = source.readInt();
        this.title = source.readString();
        this.popularity = source.readDouble();
        this.poster_path = source.readString();
        this.original_language = source.readString();
        this.original_title = source.readString();
        this.backdrop_path = source.readString();
        this.adult = source.readString();
        this.overview = source.readString();
        this.release_date = source.readString();
    }


    public boolean isBookmarked() {
        return isBookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        isBookmarked = bookmarked;
    }

    private boolean isBookmarked = false;

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String isVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String isAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.vote_count);
        dest.writeString(this.video);
        dest.writeDouble(this.vote_average);
        dest.writeString(this.title );
        dest.writeDouble(this.popularity);
        dest.writeString(this.poster_path);
        dest.writeString(this.original_language);
        dest.writeString(this.original_title);
        dest.writeString(this.backdrop_path );
        dest.writeString(this.adult);
        dest.writeString(this.overview);
        dest.writeString(this.release_date);
    }
}
