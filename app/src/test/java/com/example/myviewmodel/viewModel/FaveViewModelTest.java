package com.example.myviewmodel.viewModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.example.myviewmodel.data.Repository;
import com.example.myviewmodel.data.source.local.entity.MovieEntity;
import com.example.myviewmodel.data.source.local.entity.TvEntity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FaveViewModelTest {
    private FaveViewModel faveViewModel;

    @Mock
    private Observer<PagedList<MovieEntity>> observer;
    @Mock
    private Observer<PagedList<TvEntity>> observerTv;

    @Mock
    private PagedList<MovieEntity> pagedList;

    @Mock
    private PagedList<TvEntity> pagedListTv;

    @Mock
    private Repository repository;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        faveViewModel = new FaveViewModel(repository);
    }

    @Test
    public void getFaveMovie() {
        PagedList<MovieEntity> resource = pagedList;
        when(resource.size()).thenReturn(1);
        MutableLiveData<PagedList<MovieEntity>> movies = new MutableLiveData<>();
        movies.setValue(resource);

        when(repository.getFaveMovie()).thenReturn(movies);
        List<MovieEntity> movieEntities = faveViewModel.getFaveMovie().getValue();
        verify(repository).getFaveMovie();
        assertNotNull(movieEntities);
        assertEquals(1, movieEntities.size());
        faveViewModel.getFaveMovie().observeForever(observer);
        verify(observer).onChanged(resource);
    }

    @Test
    public void getFaveTv() {
        PagedList<TvEntity> resource = pagedListTv;
        when(resource.size()).thenReturn(1);
        MutableLiveData<PagedList<TvEntity>> tv = new MutableLiveData<>();
        tv.setValue(resource);

        when(repository.getFaveTv()).thenReturn(tv);
        List<TvEntity> tvEntities = faveViewModel.getFaveTv().getValue();
        verify(repository).getFaveTv();
        assertNotNull(tvEntities);
        assertEquals(1, tvEntities.size());
        faveViewModel.getFaveTv().observeForever(observerTv);
        verify(observerTv).onChanged(resource);
    }
}
