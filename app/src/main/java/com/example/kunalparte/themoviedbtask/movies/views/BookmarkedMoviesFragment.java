package com.example.kunalparte.themoviedbtask.movies.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kunalparte.themoviedbtask.R;
import com.example.kunalparte.themoviedbtask.movies.adapters.MoviesListAdapter;
import com.example.kunalparte.themoviedbtask.movies.db.MoviesViewModel;
import com.example.kunalparte.themoviedbtask.movies.db.ViewModelDataProvider;
import com.example.kunalparte.themoviedbtask.movies.interfaces.RecyclerItemClicked;
import com.example.kunalparte.themoviedbtask.movies.interfaces.ViewModelViewInterface;
import com.example.kunalparte.themoviedbtask.movies.models.Movies;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BookmarkedMoviesFragment extends Fragment implements ViewModelViewInterface, RecyclerItemClicked {

    @BindView(R.id.moviewListRecyclerView)
    RecyclerView moviewRecyclerView;

    private GridLayoutManager staggeredGridLayoutManager;
    private MoviesListAdapter moviesListAdapter;
    private MoviesViewModel moviesViewModel;
    private ViewModelDataProvider viewModelDataProvider;
    private List<Movies> allMoviesList;
    private int pageCount = 1;

    public BookmarkedMoviesFragment() {
        // Required empty public constructor
    }

    public static BookmarkedMoviesFragment newInstance() {
        BookmarkedMoviesFragment fragment = new BookmarkedMoviesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_bookmarked_movies, container, false);
        ButterKnife.bind(this,view);
        init();
        return view;
    }

    public void init(){
        allMoviesList = new ArrayList<>();
        moviesViewModel = ViewModelProviders.of(getActivity()).get(MoviesViewModel.class);
        viewModelDataProvider = new ViewModelDataProvider(getActivity(), moviesViewModel,this);
        viewModelDataProvider.getBookmarkedMovies();
        staggeredGridLayoutManager = new GridLayoutManager(getActivity(),2);
        staggeredGridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        moviewRecyclerView.setLayoutManager(staggeredGridLayoutManager);
    }

    public void setDataOnRecyclerView(List<Movies> movies){
        moviesListAdapter = new MoviesListAdapter(getActivity(),movies,this, viewModelDataProvider);
        moviewRecyclerView.setAdapter(moviesListAdapter);
        moviesListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void getMovies(List<Movies> movies) {
        if (movies.size() > 0) {
            allMoviesList.addAll(movies);
            setDataOnRecyclerView(movies);
        }
    }

    @Override
    public void getMovie(Movies movies) {

    }

    @Override
    public void showFooter(boolean show) {

    }

    @Override
    public void onrecyclerViewItemClicked(int position) {

    }
    @Override
    public void onResume(){
        super.onResume();
    }
}
