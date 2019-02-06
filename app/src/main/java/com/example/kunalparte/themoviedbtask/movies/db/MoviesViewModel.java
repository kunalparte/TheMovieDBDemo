package com.example.kunalparte.themoviedbtask.movies.db;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PagedList;
import android.graphics.Movie;
import android.support.annotation.NonNull;

import com.example.kunalparte.themoviedbtask.movies.models.Movies;

import java.util.List;
import java.util.logging.Handler;

public class MoviesViewModel extends AndroidViewModel {

    private MoviesRepo moviesRepo;
    private MutableLiveData<List<Movies>> movies;

    int offset = 0;

    public MoviesViewModel(@NonNull Application application) {
        super(application);
        moviesRepo = new MoviesRepo(application);
    }

//    public void insert(Movies movies){
//        moviesRepo.insert(movies);
//    }

    public void deleteMovie(Movies movies){
        moviesRepo.deleteMovie(movies);
    }

    public void updateMovie(Movies movies){
        moviesRepo.updateMovie(movies);
    }

    public LiveData<List<Movies>> getAllMovies(int offset, int rowCount){
        return moviesRepo.getAllMovies(offset,rowCount);
    }

    public LiveData<List<Movies>> getBookMarkedMovies(){
        return moviesRepo.getBookmarkedMovies();
    }

    public LiveData<List<Movies>> getSearchedList(String name ){
        return moviesRepo.getSearchedMoviesList(name);
    }

    public LiveData<Movies> getMovie(int id){
       return moviesRepo.getMovie(id);
    }

//    public LiveData<List<Movies>> getAllMovies() {
//        return moviesRepo.getAllMovies();
//    }

    public LiveData<List<Movies>> getAllMovies(){
        return moviesRepo.getAllMovies();
    }
    public void getMovies(int pageCount){
        moviesRepo.getMoviesList(pageCount);
    }
}
