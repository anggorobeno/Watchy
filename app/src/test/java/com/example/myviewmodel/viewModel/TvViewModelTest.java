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
import com.example.myviewmodel.data.source.local.entity.TvEntity;
import com.example.myviewmodel.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TvViewModelTest {
    private TvViewModel tvViewModel;

    @Mock
    private Repository repository;
    @Mock
    private Observer<Resource<PagedList<TvEntity>>> observer;
    @Mock
    private PagedList<TvEntity> pagedList;


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        tvViewModel = new TvViewModel(repository);

    }

    @Test
    public void getTv() {
        Resource<PagedList<TvEntity>> resource = Resource.success(pagedList);
        when(resource.data.size()).thenReturn(1);
        MutableLiveData<Resource<PagedList<TvEntity>>> tv = new MutableLiveData<>();
        tv.setValue(resource);

        when(repository.tvLiveData()).thenReturn(tv);
        List<TvEntity> tvEntities = tvViewModel.tvLiveData().getValue().data;
        verify(repository).tvLiveData();
        assertNotNull(tvEntities);
        assertEquals(1, tvEntities.size());
        tvViewModel.tvLiveData().observeForever(observer);
        verify(observer).onChanged(resource);

    }
}
