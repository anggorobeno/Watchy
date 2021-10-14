package com.example.myviewmodel.di;

import android.app.Application;

import com.example.myviewmodel.data.Repository;
import com.example.myviewmodel.data.source.local.LocalDataSource;
import com.example.myviewmodel.data.source.local.room.MovieDatabase;
import com.example.myviewmodel.data.source.remote.RemoteDataSource;
import com.example.myviewmodel.utils.ApiHelper;
import com.example.myviewmodel.utils.AppExecutors;

public class Injection {
    public static Repository provideRepository(Application application) {
        MovieDatabase database = MovieDatabase.getInstance(application);
        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance(new ApiHelper(application));
        LocalDataSource localDataSource = LocalDataSource.getInstance(database.movieDAO());
        AppExecutors appExecutors = new AppExecutors();
        return Repository.getInstance(remoteDataSource, localDataSource, appExecutors);
    }
}
