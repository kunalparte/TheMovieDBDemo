package com.example.kunalparte.themoviedbtask.movies.apis;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.example.kunalparte.themoviedbtask.movies.models.Movies;
import com.example.kunalparte.themoviedbtask.movies.models.MoviesMain;
import com.example.kunalparte.themoviedbtask.utils.Consts;
import com.example.kunalparte.themoviedbtask.utils.ServiceGenerater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesDataSource extends PageKeyedDataSource<Integer, Movies> {

    public static final int PAGE_SIZE = 50;

    private static final int FIRST_PAGE = 1;



    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Movies> callback) {
        MoviesClient moviesClient = ServiceGenerater.createProductApiService(MoviesClient.class);
        Call<MoviesMain> moviesMainCall = moviesClient.getMoviesList(Consts.API_KEY_AUTH,FIRST_PAGE);
        moviesMainCall.enqueue(new Callback<MoviesMain>() {
            @Override
            public void onResponse(Call<MoviesMain> call, Response<MoviesMain> response) {

                //passing the loaded data and next page value
                callback.onResult(response.body().getResults(), null,FIRST_PAGE+1);
            }

            @Override
            public void onFailure(Call<MoviesMain> call, Throwable t) {
                String err = t.getMessage();
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movies> callback) {
        MoviesClient moviesClient = ServiceGenerater.createProductApiService(MoviesClient.class);
        Call<MoviesMain> moviesMainCall = moviesClient.getMoviesList(Consts.API_KEY_AUTH,params.key);
        moviesMainCall.enqueue(new Callback<MoviesMain>() {
            @Override
            public void onResponse(Call<MoviesMain> call, Response<MoviesMain> response) {
                Integer key = params.key - 1;

                //passing the loaded data and next page value
                callback.onResult(response.body().getResults(), key);
            }

            @Override
            public void onFailure(Call<MoviesMain> call, Throwable t) {
                String err = t.getMessage();
            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movies> callback) {
        MoviesClient moviesClient = ServiceGenerater.createProductApiService(MoviesClient.class);
        Call<MoviesMain> moviesMainCall = moviesClient.getMoviesList(Consts.API_KEY_AUTH,params.key);
        moviesMainCall.enqueue(new Callback<MoviesMain>() {
            @Override
            public void onResponse(Call<MoviesMain> call, Response<MoviesMain> response) {
                Integer key = params.key+1;

                //passing the loaded data and next page value
                callback.onResult(response.body().getResults(), key);
            }

            @Override
            public void onFailure(Call<MoviesMain> call, Throwable t) {
                String err = t.getMessage();
            }
        });
    }
}
