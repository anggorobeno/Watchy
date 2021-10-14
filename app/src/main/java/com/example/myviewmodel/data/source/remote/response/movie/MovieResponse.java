package com.example.myviewmodel.data.source.remote.response.movie;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {


    @SerializedName("results")
    private List<MovieResult> results;


    public List<MovieResult> getResults() {
        return results;
    }

}