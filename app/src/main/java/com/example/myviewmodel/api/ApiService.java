package com.example.myviewmodel.api;

import com.example.myviewmodel.data.source.remote.response.movie.MovieResponse;
import com.example.myviewmodel.data.source.remote.response.movie.MovieResult;
import com.example.myviewmodel.data.source.remote.response.tv.TvResponse;
import com.example.myviewmodel.data.source.remote.response.tv.TvResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/popular")
    Call<MovieResponse> getPopularMovie(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

    @GET("movie/{movie_id}")
    Call<MovieResult> getDetailMovie(
            @Path("movie_id") long id,
            @Query("api_key") String apiKey
    );

    @GET("tv/popular")
    Call<TvResponse> getPopularTv(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

    @GET("tv/{tv_id}")
    Call<TvResult> getDetailTv(
            @Path("tv_id") long id,
            @Query("api_key") String apiKey
    );

}
