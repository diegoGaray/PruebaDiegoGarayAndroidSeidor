package com.seidor.diego.pruebadiegogarayseidor.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

//Modelo de popular rated que responde a la url: https://.../popular?api_key=34738023d27013e6d1b995443764da44
public class PopularRated implements Serializable {

    @SerializedName("vote_count")
    private String vote_count;
    @SerializedName("id")
    private String id;
    @SerializedName("vote_average")
    private String vote_average;
    @SerializedName("title")
    private String title;
    @SerializedName("popularity")
    private String popularity;
    @SerializedName("poster_path")
    private String poster_path;
    @SerializedName("original_language")
    private String original_language;
    @SerializedName("release_date")
    private String release_date;
    @SerializedName("overview")
    private String overview;

    public PopularRated() {
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
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

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Override
    public String toString() {
        return "PopularRated{" +
                "vote_count='" + vote_count + '\'' +
                ", id='" + id + '\'' +
                ", vote_average='" + vote_average + '\'' +
                ", title='" + title + '\'' +
                ", popularity='" + popularity + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", original_language='" + original_language + '\'' +
                ", release_date='" + release_date + '\'' +
                ", overview='" + overview + '\'' +
                '}';
    }
}