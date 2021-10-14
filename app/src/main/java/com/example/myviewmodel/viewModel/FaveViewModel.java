package com.example.myviewmodel.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.example.myviewmodel.data.Repository;
import com.example.myviewmodel.data.source.local.entity.MovieEntity;
import com.example.myviewmodel.data.source.local.entity.TvEntity;

public class FaveViewModel extends ViewModel {
    private final Repository repository;

    public FaveViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<PagedList<MovieEntity>> getFaveMovie() {
        return repository.getFaveMovie();
    }

    public LiveData<PagedList<TvEntity>> getFaveTv() {
        return repository.getFaveTv();
    }

    public void setFaveMoviePaged(MovieEntity movieEntity) {
        final boolean state = !movieEntity.isFave();
        repository.setFaveMovie(movieEntity, state);
    }

    public void setFaveTvPaged(TvEntity tvEntity) {
        final boolean state = !tvEntity.isFave();
        repository.setFaveTv(tvEntity, state);
    }
}
