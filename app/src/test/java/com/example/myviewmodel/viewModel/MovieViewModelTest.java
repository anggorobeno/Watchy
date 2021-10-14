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
import com.example.myviewmodel.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MovieViewModelTest {
    private MovieViewModel movieViewModel;

    @Mock
    private Repository repository;
    @Mock
    private Observer<Resource<PagedList<MovieEntity>>> observer;
    @Mock
    private PagedList<MovieEntity> pagedList;


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        movieViewModel = new MovieViewModel(repository);

    }

    @Test
    public void getMovieList() {
        Resource<PagedList<MovieEntity>> resource = Resource.success(pagedList);
        when(resource.data.size()).thenReturn(1);
        MutableLiveData<Resource<PagedList<MovieEntity>>> movies = new MutableLiveData<>();
        movies.setValue(resource);

        when(repository.movieLiveData()).thenReturn(movies);
        List<MovieEntity> movieEntities = movieViewModel.movieLiveData().getValue().data;
        verify(repository).movieLiveData();
        assertNotNull(movieEntities);
        assertEquals(1, movieEntities.size());
        movieViewModel.movieLiveData().observeForever(observer);
        verify(observer).onChanged(resource);

    }
}
