package com.example.myviewmodel.data.source.remote.response.tv;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvResponse {


    @SerializedName("results")
    private List<TvResult> results;


    public List<TvResult> getResults() {
        return results;
    }

}