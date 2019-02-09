package com.example.kunalparte.themoviedbtask.movies.db;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.support.annotation.Nullable;

import com.example.kunalparte.themoviedbtask.movies.interfaces.ViewModelViewInterface;
import com.example.kunalparte.themoviedbtask.movies.models.Movies;
import com.example.kunalparte.themoviedbtask.utils.Consts;

import java.util.List;

public class ViewModelDataProvider {

    private Activity activity;
    private MoviesViewModel moviesViewModel;
    private ViewModelViewInterface viewModelViewInterface;

    public ViewModelDataProvider(Activity activity, MoviesViewModel moviesViewModel, ViewModelViewInterface viewModelViewInterface){
        this.activity = activity;
        this.moviesViewModel = moviesViewModel;
        this.viewModelViewInterface = viewModelViewInterface;
    }

    public void getBookmarkedMovies(){
        moviesViewModel.getBookMarkedMovies().observe((LifecycleOwner) activity, new Observer<List<Movies>>() {
            @Override
            public void onChanged(@Nullable List<Movies> movies) {
                viewModelViewInterface.getMovies(movies);
            }
        });
    }

    public void getMovies(int pageNo){
        moviesViewModel.getMovies(pageNo);

    }

    public void getAllMovies(){
        moviesViewModel.getAllMovies().observe((LifecycleOwner) activity, new Observer<List<Movies>>() {
            @Override
            public void onChanged(@Nullable List<Movies> movies) {
                if (Consts.pageCount ==1)
                viewModelViewInterface.getMovies(movies);
            }
        });
    }

    public void getAllMovi(int offset, int rowCount){
        viewModelViewInterface.showFooter(true);
        moviesViewModel.getAllMovies(offset,rowCount).observe((LifecycleOwner) activity, new Observer<List<Movies>>() {
            @Override
            public void onChanged(@Nullable List<Movies> movies) {
                if (movies.size() > 0) {
                    viewModelViewInterface.getMovies(movies);
                    viewModelViewInterface.showFooter(false);
                }
            }
        });
    }

    public void getSearchedMoviesList(String name){
        moviesViewModel.getSearchedList(name).observe((LifecycleOwner) activity, new Observer<List<Movies>>() {
            @Override
            public void onChanged(@Nullable List<Movies> movies) {
                viewModelViewInterface.getMovies(movies);
            }
        });
    }

    public void deleteMovie(Movies movies){
        moviesViewModel.deleteMovie(movies);
    }

    public void updateMovie(Movies movies){
        moviesViewModel.updateMovie(movies);
    }

    public void getMovie(int id ){
        moviesViewModel.getMovie(id).observe((LifecycleOwner) activity, new Observer<Movies>() {
            @Override
            public void onChanged(@Nullable Movies movies) {
                viewModelViewInterface.getMovie(movies);
            }
        });
    }
}
