package com.example.myviewmodel.viewModel;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.myviewmodel.data.Repository;
import com.example.myviewmodel.data.source.local.entity.MovieEntity;
import com.example.myviewmodel.data.source.local.entity.TvEntity;
import com.example.myviewmodel.vo.Resource;
import com.example.utils.FakeDataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DetailViewModelTest {
    private DetailViewModel detailViewModel;

    @Mock
    private Repository repository;

    @Mock
    private Observer<Resource<MovieEntity>> observer;

    @Mock
    private Observer<Resource<TvEntity>> observerTv;

    private final Resource<MovieEntity> dummyMovie = Resource.success(FakeDataDummy.generatePopularMovie().get(0));
    private final Resource<TvEntity> dummyTv = Resource.success(FakeDataDummy.generatePopularTv().get(0));

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        detailViewModel = new DetailViewModel(repository);
        detailViewModel.setIdMovie(dummyMovie.data.getId());
        detailViewModel.setIdTv(dummyTv.data.getId());

    }

    @Test
    public void getMovieDetails() {
        MutableLiveData<Resource<MovieEntity>> movieMutableLiveData = new MutableLiveData<>();
        movieMutableLiveData.setValue(dummyMovie);
        when(repository.selectedMovieLiveData(dummyMovie.data.getId())).thenReturn(movieMutableLiveData);
        detailViewModel.getSelectedMovie().observeForever(observer);
        verify(observer).onChanged(dummyMovie);
    }

    @Test
    public void getTvDetails() {
        MutableLiveData<Resource<TvEntity>> tvLiveData = new MutableLiveData<>();
        tvLiveData.setValue(dummyTv);
        when(repository.selectedTvLiveData(dummyTv.data.getId())).thenReturn(tvLiveData);
        detailViewModel.getSelectedTv().observeForever(observerTv);
        verify(observerTv).onChanged(dummyTv);
    }
}
