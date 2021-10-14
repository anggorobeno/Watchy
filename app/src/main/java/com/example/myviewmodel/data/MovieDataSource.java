package com.example.myviewmodel.data;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.myviewmodel.data.source.local.entity.MovieEntity;
import com.example.myviewmodel.data.source.local.entity.TvEntity;
import com.example.myviewmodel.vo.Resource;

public interface MovieDataSource {
    LiveData<Resource<PagedList<MovieEntity>>> movieLiveData();

    LiveData<Resource<PagedList<TvEntity>>> tvLiveData();

    LiveData<Resource<MovieEntity>> selectedMovieLiveData(long movieId);

    LiveData<Resource<TvEntity>> selectedTvLiveData(long tvId);

    LiveData<PagedList<MovieEntity>> getFaveMovie();

    LiveData<PagedList<TvEntity>> getFaveTv();

    void setFaveMovie(MovieEntity movieEntity, boolean state);

    void setFaveTv(TvEntity tvEntity, boolean state);


}
