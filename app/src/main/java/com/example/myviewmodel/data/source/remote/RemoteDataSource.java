package com.example.myviewmodel.data.source.remote;

import androidx.lifecycle.LiveData;

import com.example.myviewmodel.data.Repository;
import com.example.myviewmodel.data.source.remote.response.movie.MovieResult;
import com.example.myviewmodel.data.source.remote.response.tv.TvResult;
import com.example.myviewmodel.utils.ApiHelper;

import java.util.List;

public class RemoteDataSource {
    private static RemoteDataSource INSTANCE = null;
    private final ApiHelper apiHelper;

    public RemoteDataSource(ApiHelper apiHelper) {
        this.apiHelper = apiHelper;
    }

    public static RemoteDataSource getInstance(ApiHelper apiHelper) {
        if (INSTANCE == null) {
            synchronized (Repository.class) {
                INSTANCE = new RemoteDataSource(apiHelper);
            }
        }
        return INSTANCE;
    }

    public LiveData<ApiResponse<List<MovieResult>>> movieLiveData() {
        return apiHelper.getPopularMovie();
    }

    public LiveData<ApiResponse<List<TvResult>>> tvLiveData() {
        return apiHelper.getPopularTv();
    }

    public LiveData<ApiResponse<MovieResult>> selectedMovieLiveData(long id) {
        return apiHelper.getSelectedMovie(id);
    }

    public LiveData<ApiResponse<TvResult>> selectedTvLiveData(long id) {
        return apiHelper.getSelectedTv(id);
    }
}
