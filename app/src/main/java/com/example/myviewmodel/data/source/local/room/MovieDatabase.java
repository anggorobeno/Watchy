package com.example.myviewmodel.data.source.local.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myviewmodel.data.source.local.entity.MovieEntity;
import com.example.myviewmodel.data.source.local.entity.TvEntity;

@Database(entities = {MovieEntity.class, TvEntity.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDao movieDAO();

    public static volatile MovieDatabase INSTANCE;

    public static MovieDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (MovieDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        MovieDatabase.class, "Movies.db")
                        .build();
            }
        }
        return INSTANCE;
    }
}
