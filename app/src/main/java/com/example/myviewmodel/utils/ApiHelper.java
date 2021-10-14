package com.example.myviewmodel.utils;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myviewmodel.api.ApiConfig;
import com.example.myviewmodel.data.source.remote.ApiResponse;
import com.example.myviewmodel.data.source.remote.response.movie.MovieResponse;
import com.example.myviewmodel.data.source.remote.response.movie.MovieResult;
import com.example.myviewmodel.data.source.remote.response.tv.TvResponse;
import com.example.myviewmodel.data.source.remote.response.tv.TvResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiHelper {

    public ApiHelper(Application application) {
    }

    public LiveData<ApiResponse<List<MovieResult>>> getPopularMovie() {
        Espresso.increment();
        MutableLiveData<ApiResponse<List<MovieResult>>> movie = new MutableLiveData<>();
        Call<MovieResponse> movieResponseCall = ApiConfig.getApiConfig().getPopularMovie(Constant.API_KEY, 1);
        movieResponseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    movie.setValue(ApiResponse.success(response.body().getResults()));
                    Espresso.decrement();
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {

            }
        });
        return movie;
    }

    public LiveData<ApiResponse<List<TvResult>>> getPopularTv() {
        Espresso.increment();
        MutableLiveData<ApiResponse<List<TvResult>>> tv = new MutableLiveData<>();
        Call<TvResponse> tvResponseCall = ApiConfig.getApiConfig().getPopularTv(Constant.API_KEY, 1);
        tvResponseCall.enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(@NonNull Call<TvResponse> call, @NonNull Response<TvResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        tv.setValue(ApiResponse.success(response.body().getResults()));
                        Espresso.decrement();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<TvResponse> call, @NonNull Throwable t) {

            }
        });
        return tv;
    }

    public LiveData<ApiResponse<MovieResult>> getSelectedMovie(long id) {
        Espresso.increment();
        MutableLiveData<ApiResponse<MovieResult>> selectedMovie = new MutableLiveData<>();
        Call<MovieResult> movieResultCall = ApiConfig.getApiConfig().getDetailMovie(id, Constant.API_KEY);
        movieResultCall.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(@NonNull Call<MovieResult> call, @NonNull Response<MovieResult> response) {
                if (response.isSuccessful() && response.body() != null) {
                    selectedMovie.setValue(ApiResponse.success(response.body()));
                    Espresso.decrement();
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResult> call, @NonNull Throwable t) {

            }
        });
        return selectedMovie;
    }

    public LiveData<ApiResponse<TvResult>> getSelectedTv(long id) {
        Espresso.increment();
        MutableLiveData<ApiResponse<TvResult>> selectedTv = new MutableLiveData<>();
        Call<TvResult> tvResultCall = ApiConfig.getApiConfig().getDetailTv(id, Constant.API_KEY);
        tvResultCall.enqueue(new Callback<TvResult>() {
            @Override
            public void onResponse(@NonNull Call<TvResult> call, @NonNull Response<TvResult> response) {
                if (response.isSuccessful() && response.body() != null) {
                    selectedTv.setValue(ApiResponse.success(response.body()));
                    Espresso.decrement();

                }
            }

            @Override
            public void onFailure(@NonNull Call<TvResult> call, @NonNull Throwable t) {

            }
        });
        return selectedTv;
    }

}
