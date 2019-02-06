package com.example.kunalparte.themoviedbtask.movies.apis;

import com.example.kunalparte.themoviedbtask.movies.models.Movies;
import com.example.kunalparte.themoviedbtask.movies.models.MoviesMain;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesClient {

    @GET("discover/movie")
    Call<MoviesMain>getMoviesList(@Query("api_key") String apiKey,
                                  @Query("page") int pageNod);
}
