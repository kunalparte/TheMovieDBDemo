package com.example.kunalparte.themoviedbtask.movies.db;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.example.kunalparte.themoviedbtask.movies.models.Movies;
import com.example.kunalparte.themoviedbtask.utils.Consts;

@Database(entities = {Movies.class},version = 1,exportSchema = false)
@TypeConverters({DataTypeConverters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase appDatabase;

    public abstract MoviesDao moviesDao();

    public static AppDatabase getDatabaseInstance(Application application){
        if (appDatabase == null){
            appDatabase = Room.databaseBuilder(application.getApplicationContext(),
                    AppDatabase.class, Consts.MOVIEWS_DB)
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    //allows queries to execute on main thread
                    .allowMainThreadQueries()
                    //.addCallback(sRoomDatabaseCallback)
                    .build();
        }
        return appDatabase;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
