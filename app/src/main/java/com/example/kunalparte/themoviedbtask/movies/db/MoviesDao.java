package com.example.kunalparte.themoviedbtask.movies.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.kunalparte.themoviedbtask.movies.models.Movies;
import com.example.kunalparte.themoviedbtask.utils.Consts;

import java.util.List;

import javax.sql.DataSource;

@Dao
public interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<Movies> movies);

    @Query("Select * from "+ Consts.MOVIES_TABLE)
    LiveData<List<Movies>> getAllMovies();

    @Query("Select * from "+ Consts.MOVIES_TABLE + " limit :offset,:rowCount")
    LiveData<List<Movies>> getAllMovies(int offset, int rowCount);

    @Query("Select * from "+ Consts.MOVIES_TABLE+" where isBookmarked = 1")
    LiveData<List<Movies>> getBookMarkedMovies();

    @Query("Select * from "+ Consts.MOVIES_TABLE+" where id =:id")
    LiveData<List<Movies>> getSelectedMovies( int id );

    @Query("Select * from "+Consts.MOVIES_TABLE+" where title like :name||'%' or title like '%'||:name||'%' or title like '%'||:name")
    LiveData<List<Movies>> getSearchedMoviesList(String name);

    @Delete
    void delete(Movies movies);

    @Update
    void update(Movies movies);

    @Query("Select * from "+Consts.MOVIES_TABLE+" where id =:id")
    LiveData<Movies> getMovie(int id );

}
