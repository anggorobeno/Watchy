package com.example.myviewmodel.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import com.example.myviewmodel.data.source.local.LocalDataSource;
import com.example.myviewmodel.data.source.local.entity.MovieEntity;
import com.example.myviewmodel.data.source.local.entity.TvEntity;
import com.example.myviewmodel.data.source.remote.RemoteDataSource;
import com.example.myviewmodel.utils.AppExecutors;
import com.example.myviewmodel.vo.Resource;
import com.example.utils.FakeDataDummy;
import com.example.utils.LiveDataTestUtil;
import com.example.utils.PagedListUtil;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

public class FakeRepositoryTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private final LocalDataSource localDataSource = mock(LocalDataSource.class);
    private final RemoteDataSource remoteDataSource = mock(RemoteDataSource.class);
    private final AppExecutors appExecutors = mock(AppExecutors.class);
    private final FakeRepository fakeRepository = new FakeRepository(remoteDataSource, localDataSource, appExecutors);


    private final List<MovieEntity> movies = FakeDataDummy.generatePopularMovie();
    private final List<TvEntity> tvs = FakeDataDummy.generatePopularTv();

    private final MovieEntity movie = FakeDataDummy.generatePopularMovie().get(0);
    private final TvEntity tv = FakeDataDummy.generatePopularTv().get(0);

    private final long movieId = movie.getId();
    private final long tvShowId = tv.getId();

    @Test
    public void getAllMovies() {
        DataSource.Factory<Integer, MovieEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(localDataSource.getAllMovie()).thenReturn(dataSourceFactory);
        fakeRepository.movieLiveData();
        Resource<PagedList<MovieEntity>> result = Resource.success(PagedListUtil.mockPagedList(movies));

        verify(localDataSource).getAllMovie();
        assertNotNull(result.data);
        assertEquals(movies.size(), result.data.size());
    }

    @Test
    public void getAllTv() {
        DataSource.Factory<Integer, TvEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(localDataSource.getAllTv()).thenReturn(dataSourceFactory);
        fakeRepository.tvLiveData();
        Resource<PagedList<TvEntity>> result = Resource.success(PagedListUtil.mockPagedList(tvs));

        verify(localDataSource).getAllTv();
        assertNotNull(result.data);
        assertEquals(tvs.size(), result.data.size());
    }

    @Test
    public void getDetailTvShow() {
        MutableLiveData<TvEntity> dummyTv = new MutableLiveData<>();
        dummyTv.setValue(tv);
        when(localDataSource.getTvById(tvShowId)).thenReturn(dummyTv);
        Resource<TvEntity> resultTvShow = LiveDataTestUtil.getValue(fakeRepository.selectedTvLiveData(tvShowId));

        verify(localDataSource).getTvById(tvShowId);
        assertNotNull(resultTvShow);
        if (resultTvShow.data != null) {
            assertEquals(tvShowId, resultTvShow.data.getId());
        }
    }

    @Test
    public void getDetailMovies() {
        MutableLiveData<MovieEntity> dummyMovie = new MutableLiveData<>();
        dummyMovie.setValue(movie);
        when(localDataSource.getMovieById(movieId)).thenReturn(dummyMovie);
        Resource<MovieEntity> resultMovie = LiveDataTestUtil.getValue(fakeRepository.selectedMovieLiveData(movieId));

        verify(localDataSource).getMovieById(movieId);
        assertNotNull(resultMovie);
        if (resultMovie.data != null) {
            assertEquals(movieId, resultMovie.data.getId());
        }
    }

    @Test
    public void getFaveMovie() {
        DataSource.Factory<Integer, MovieEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(localDataSource.getFaveMovie()).thenReturn(dataSourceFactory);
        fakeRepository.getFaveMovie();
        Resource<PagedList<MovieEntity>> result = Resource.success(PagedListUtil.mockPagedList(movies));

        verify(localDataSource).getFaveMovie();
        assertNotNull(result);
        assertEquals(movies.size(), result.data.size());
    }

    @Test
    public void getFaveTv() {
        DataSource.Factory<Integer, TvEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(localDataSource.getFaveTv()).thenReturn(dataSourceFactory);
        fakeRepository.getFaveTv();
        Resource<PagedList<TvEntity>> result = Resource.success(PagedListUtil.mockPagedList(tvs));

        verify(localDataSource).getFaveTv();
        assertNotNull(result.data);
        assertEquals(tvs.size(), result.data.size());
    }
}
