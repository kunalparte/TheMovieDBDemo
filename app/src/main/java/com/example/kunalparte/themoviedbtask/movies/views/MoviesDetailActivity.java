package com.example.kunalparte.themoviedbtask.movies.views;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kunalparte.themoviedbtask.R;
import com.example.kunalparte.themoviedbtask.movies.db.MoviesViewModel;
import com.example.kunalparte.themoviedbtask.movies.db.ViewModelDataProvider;
import com.example.kunalparte.themoviedbtask.movies.interfaces.ViewModelViewInterface;
import com.example.kunalparte.themoviedbtask.movies.models.Movies;
import com.example.kunalparte.themoviedbtask.utils.Consts;
import com.example.kunalparte.themoviedbtask.utils.GlideLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesDetailActivity extends AppCompatActivity implements ViewModelViewInterface {

    @BindView(R.id.moviesDetailImageView)
    ImageView moviesDetailImageView;

    @BindView(R.id.movieTitleTextVew)
    TextView moviesTitle;

    @BindView(R.id.movieDescTextView)
    TextView moviesDesc;

    @BindView(R.id.movieDateTextVew)
    TextView moviesDate;

    @BindView(R.id.isAdTitleTextVew)
    TextView isAdultTextView;

    @BindView(R.id.movieVotesTextVew)
    TextView moviesVites;

    private int movieId =0;
    private ViewModelDataProvider viewModelDataProvider;
    private MoviesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvies_detail);
        ButterKnife.bind(this);
        init();
    }

    public void init(){
        movieId = getIntent().getIntExtra(Consts.MOVIE_KEY,0);
        viewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        viewModelDataProvider = new ViewModelDataProvider(this, viewModel, this);
        viewModelDataProvider.getMovie(movieId);

    }

    @Override
    public void getMovies(List<Movies> movies) {

    }

    @Override
    public void getMovie(Movies movies) {
        if (movies != null){
            GlideLoader
                    .url(this)
                    .load(Consts.IMAGE_BASE_URL + movies.getPoster_path()).into(moviesDetailImageView);
            moviesTitle.setText(movies.getTitle());
            moviesDesc.setText(movies.getOverview());
            moviesDate.setText(movies.getRelease_date());
            isAdultTextView.setCompoundDrawablePadding(4);
            if (movies.isAdult().equals("true")) {
                isAdultTextView.setText("Adult");
                isAdultTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.plus_18_movie, 0, 0, 0);
            }else{
                isAdultTextView.setText("Not adultAdult");
                    isAdultTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.family,0,0,0);
                }
            moviesVites.setText(""+movies.getVote_count());
        }
    }


    @Override
    public void showFooter(boolean show) {

    }
}
