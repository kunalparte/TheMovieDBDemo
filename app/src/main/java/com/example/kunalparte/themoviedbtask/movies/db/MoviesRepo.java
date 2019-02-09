package com.example.kunalparte.themoviedbtask.movies.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;
import android.graphics.Movie;
import android.os.AsyncTask;

import com.example.kunalparte.themoviedbtask.movies.apis.MoviesClient;
import com.example.kunalparte.themoviedbtask.movies.apis.MoviesDataSource;
import com.example.kunalparte.themoviedbtask.movies.models.Movies;
import com.example.kunalparte.themoviedbtask.movies.models.MoviesMain;
import com.example.kunalparte.themoviedbtask.utils.Consts;
import com.example.kunalparte.themoviedbtask.utils.ServiceGenerater;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepo {

    private AppDatabase appDatabase;

    private MoviesDao moviesDao;

    public LiveData<PagedList<Movies>> moviesLiveList;

    private MoviesDataSourceFactory moviesDataSourceFactory;

    private MutableLiveData<List<Movies>> moviesList;

    PagedList.Config pagedListConfig;




    public MoviesRepo(Application application){
        appDatabase = AppDatabase.getDatabaseInstance(application);
        moviesDao = appDatabase.moviesDao();
    }


    public void insert(List<Movies> movies){
        new InsertMoviesTask(moviesDao,movies).execute();
    }

    public void deleteMovie(Movies movies){
        moviesDao.delete(movies);
    }

    public LiveData<List<Movies>> getBookmarkedMovies(){
        return moviesDao.getBookMarkedMovies();
    }

    public LiveData<List<Movies>> getAllMovies(int offset, int rowCount){
        return moviesDao.getAllMovies(offset,rowCount);
    }

    public LiveData<List<Movies>> getAllMovies(){
        return moviesDao.getAllMovies();
    }

    public LiveData<Movies> getMovie(int id ){
        return moviesDao.getMovie(id);
    }

    public LiveData<List<Movies>> getSearchedMoviesList( String name){
        return moviesDao.getSearchedMoviesList(name);
    }

    public void updateMovie(Movies movies){
        moviesDao.update(movies);
    }

    public void getMoviesList(int pageNo){
        moviesList = new MutableLiveData<>();
        MoviesClient moviesClient = ServiceGenerater.createProductApiService(MoviesClient.class);
        Call<MoviesMain> moviesMainCall = moviesClient.getMoviesList(Consts.API_KEY_AUTH,pageNo);
        moviesMainCall.enqueue(new Callback<MoviesMain>() {
            @Override
            public void onResponse(Call<MoviesMain> call, Response<MoviesMain> response) {
                MoviesMain main = response.body();
                insert(main.getResults());
                Consts.pageCount = main.getPage();
                Consts.isDataUpdated = true;
            }

            @Override
            public void onFailure(Call<MoviesMain> call, Throwable t) {
                String err = t.getMessage();
            }
        });
    }

    public class InsertMoviesTask extends AsyncTask<Void, Void, Void>{

        private MoviesDao moviesDao;
        private List<Movies> movies;

        public InsertMoviesTask(MoviesDao moviesDao, List<Movies> movies){
            this.movies = movies;
            this.moviesDao = moviesDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            moviesDao.insert(movies);
            return null;
        }
    }
}
