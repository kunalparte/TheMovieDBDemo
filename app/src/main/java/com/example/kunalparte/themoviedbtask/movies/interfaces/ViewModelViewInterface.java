package com.example.kunalparte.themoviedbtask.movies.interfaces;

import android.arch.paging.PagedList;

import com.example.kunalparte.themoviedbtask.movies.models.Movies;

import java.util.List;

public interface ViewModelViewInterface {

    void getMovies(List<Movies> movies);
    void getMovie(Movies movies);
    void showFooter(boolean show);
}
