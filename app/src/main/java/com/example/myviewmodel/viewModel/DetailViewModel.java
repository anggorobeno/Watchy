package com.example.myviewmodel.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.myviewmodel.data.Repository;
import com.example.myviewmodel.data.source.local.entity.MovieEntity;
import com.example.myviewmodel.data.source.local.entity.TvEntity;
import com.example.myviewmodel.vo.Resource;

public class DetailViewModel extends ViewModel {
    private Repository repository;
    private final MutableLiveData<Long> idMovie = new MutableLiveData<>();
    private final MutableLiveData<Long> idTv = new MutableLiveData<>();
    private final LiveData<Resource<MovieEntity>> movie = Transformations.switchMap(idMovie, idMovie -> repository.selectedMovieLiveData(idMovie));
    private final LiveData<Resource<TvEntity>> tv = Transformations.switchMap(idTv, mIdTv -> repository.selectedTvLiveData(mIdTv));

    public DetailViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<Resource<MovieEntity>> getSelectedMovie() {
        return movie;
    }

    public LiveData<Resource<TvEntity>> getSelectedTv() {
        return tv;
    }

    public void setIdMovie(long idMovie) {
        this.idMovie.setValue(idMovie);
    }

    public void setIdTv(long idTv) {
        this.idTv.setValue(idTv);
    }

    public void setFaveMovie() {
        if (movie.getValue() != null) {
            MovieEntity movieEntity = movie.getValue().data;
            if (movieEntity != null) {
                final boolean state = !movieEntity.isFave();
                repository.setFaveMovie(movieEntity, state);
            }
        }
    }

    public void setFaveTv() {
        if (getSelectedTv().getValue() != null) {
            TvEntity tvEntity = getSelectedTv().getValue().data;
            if (tvEntity != null) {
                final boolean state = !tvEntity.isFave();
                repository.setFaveTv(tvEntity, state);
            }
        }
    }

}
