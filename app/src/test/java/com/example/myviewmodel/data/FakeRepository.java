package com.example.myviewmodel.data;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.myviewmodel.data.source.local.LocalDataSource;
import com.example.myviewmodel.data.source.local.entity.MovieEntity;
import com.example.myviewmodel.data.source.local.entity.TvEntity;
import com.example.myviewmodel.data.source.remote.ApiResponse;
import com.example.myviewmodel.data.source.remote.RemoteDataSource;
import com.example.myviewmodel.data.source.remote.response.movie.MovieResult;
import com.example.myviewmodel.data.source.remote.response.tv.TvResult;
import com.example.myviewmodel.utils.AppExecutors;
import com.example.myviewmodel.vo.Resource;

import java.util.ArrayList;
import java.util.List;

public class FakeRepository implements MovieDataSource {
    private static final FakeRepository INSTANCE = null;
    private final RemoteDataSource remoteDataSource;
    private final LocalDataSource localDataSource;
    private final AppExecutors appExecutors;

    public FakeRepository(RemoteDataSource remoteDataSource, LocalDataSource localDataSource, AppExecutors appExecutors) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        this.appExecutors = appExecutors;
    }

    @Override
    public LiveData<Resource<PagedList<MovieEntity>>> movieLiveData() {
        return new NetworkBoundResource<PagedList<MovieEntity>, List<MovieResult>>(appExecutors) {
            @Override
            protected LiveData<PagedList<MovieEntity>> loadFromDB() {
                PagedList.Config config = new PagedList.Config.Builder()
                        .setInitialLoadSizeHint(7)
                        .setPageSize(4)
                        .build();
                return new LivePagedListBuilder<>(localDataSource.getAllMovie(), config).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<MovieEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResult>>> createCall() {
                return remoteDataSource.movieLiveData();
            }

            @Override
            protected void saveCallResult(List<MovieResult> data) {
                List<MovieEntity> movieEntities = new ArrayList<>();
                for (MovieResult movieResult : data) {
                    MovieEntity movieEntity = new MovieEntity(movieResult.getId(),
                            movieResult.getOverview(),
                            movieResult.getTitle(),
                            movieResult.getPosterPath(),
                            movieResult.getBackdropPath(),
                            movieResult.getReleaseDate(),
                            movieResult.getVoteAverage(),
                            null);

                    movieEntities.add(movieEntity);
                }
                localDataSource.insertMovie(movieEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<PagedList<TvEntity>>> tvLiveData() {
        return new NetworkBoundResource<PagedList<TvEntity>, List<TvResult>>(appExecutors) {
            @Override
            protected LiveData<PagedList<TvEntity>> loadFromDB() {
                PagedList.Config config = new PagedList.Config.Builder()
                        .setPageSize(4)
                        .build();
                return new LivePagedListBuilder<>(localDataSource.getAllTv(), config).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<TvEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<TvResult>>> createCall() {
                return remoteDataSource.tvLiveData();
            }

            @Override
            protected void saveCallResult(List<TvResult> data) {
                List<TvEntity> tvEntities = new ArrayList<>();
                for (TvResult tvResult : data) {
                    TvEntity tvEntity = new TvEntity(tvResult.getId(),
                            tvResult.getFirstAirDate(),
                            tvResult.getOverview(),
                            tvResult.getName(),
                            tvResult.getPosterPath(),
                            tvResult.getBackdropPath(),
                            tvResult.getVoteAverage(),
                            null);
                    tvEntities.add(tvEntity);
                }
                localDataSource.insertTv(tvEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<MovieEntity>> selectedMovieLiveData(long movieId) {
        return new NetworkBoundResource<MovieEntity, MovieResult>(appExecutors) {
            @Override
            protected LiveData<MovieEntity> loadFromDB() {
                return localDataSource.getMovieById(movieId);
            }

            @Override
            protected Boolean shouldFetch(MovieEntity data) {
                return (data == null);
            }

            @Override
            protected LiveData<ApiResponse<MovieResult>> createCall() {
                return remoteDataSource.selectedMovieLiveData(movieId);
            }

            @Override
            protected void saveCallResult(MovieResult data) {
                List<MovieEntity> movieEntities = new ArrayList<>();
                MovieEntity movieEntity = new MovieEntity(data.getId(),
                        data.getOverview(),
                        data.getTitle(),
                        data.getPosterPath(),
                        data.getBackdropPath(),
                        data.getReleaseDate(),
                        data.getVoteAverage(),
                        null);
                movieEntities.add(movieEntity);
                localDataSource.insertMovie(movieEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<TvEntity>> selectedTvLiveData(long tvId) {
        return new NetworkBoundResource<TvEntity, TvResult>(appExecutors) {
            @Override
            protected LiveData<TvEntity> loadFromDB() {
                return localDataSource.getTvById(tvId);
            }

            @Override
            protected Boolean shouldFetch(TvEntity data) {
                return (data == null);
            }

            @Override
            protected LiveData<ApiResponse<TvResult>> createCall() {
                return remoteDataSource.selectedTvLiveData(tvId);
            }

            @Override
            protected void saveCallResult(TvResult data) {
                TvEntity tvEntity = new TvEntity(data.getId(),
                        data.getFirstAirDate(),
                        data.getOverview(),
                        data.getName(),
                        data.getPosterPath(),
                        data.getBackdropPath(),
                        data.getVoteAverage(),
                        null);
                List<TvEntity> tvEntities = new ArrayList<>();
                tvEntities.add(tvEntity);
                localDataSource.insertTv(tvEntities);

            }
        }.asLiveData();
    }

    @Override
    public void setFaveMovie(MovieEntity movieEntity, boolean state) {
        Runnable runnable = () -> localDataSource.setFaveMovie(movieEntity, state);
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void setFaveTv(TvEntity tvEntity, boolean state) {
        Runnable runnable = () -> localDataSource.setFaveTv(tvEntity, state);
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public LiveData<PagedList<MovieEntity>> getFaveMovie() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build();
        return new LivePagedListBuilder<>(localDataSource.getFaveMovie(), config).build();
    }

    @Override
    public LiveData<PagedList<TvEntity>> getFaveTv() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build();
        return new LivePagedListBuilder<>(localDataSource.getFaveTv(), config).build();
    }
}
