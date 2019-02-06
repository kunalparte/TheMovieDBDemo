package com.example.kunalparte.themoviedbtask.movies.db;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;

import com.example.kunalparte.themoviedbtask.movies.apis.MoviesDataSource;
import com.example.kunalparte.themoviedbtask.movies.models.Movies;

public class MoviesDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Movies>> moviesLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource<Integer,Movies> create() {
        MoviesDataSource moviesDataSource = new MoviesDataSource();
        moviesLiveDataSource.postValue(moviesDataSource);
        return moviesDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer,Movies>> getMoviesLiveData(){
        return moviesLiveDataSource;
    }
}
