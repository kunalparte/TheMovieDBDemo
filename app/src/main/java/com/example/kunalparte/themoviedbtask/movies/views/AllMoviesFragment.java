package com.example.kunalparte.themoviedbtask.movies.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.kunalparte.themoviedbtask.R;
import com.example.kunalparte.themoviedbtask.movies.adapters.MoviesListAdapter;
import com.example.kunalparte.themoviedbtask.movies.adapters.MoviesPageListAdapter;
import com.example.kunalparte.themoviedbtask.movies.db.MoviesViewModel;
import com.example.kunalparte.themoviedbtask.movies.db.ViewModelDataProvider;
import com.example.kunalparte.themoviedbtask.movies.interfaces.RecyclerItemClicked;
import com.example.kunalparte.themoviedbtask.movies.interfaces.ViewModelViewInterface;
import com.example.kunalparte.themoviedbtask.movies.models.Movies;
import com.example.kunalparte.themoviedbtask.utils.Consts;
import com.example.kunalparte.themoviedbtask.utils.ScrollToPaginateListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AllMoviesFragment extends Fragment implements ViewModelViewInterface,RecyclerItemClicked,TextWatcher {

    @BindView(R.id.moviewListRecyclerView)
    RecyclerView moviewRecyclerView;

    @BindView(R.id.searchEditText)
    EditText searchedEditText;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private GridLayoutManager staggeredGridLayoutManager;
    private MoviesListAdapter moviesListAdapter;
    private MoviesViewModel moviesViewModel;
    private ViewModelDataProvider viewModelDataProvider;
    private List<Movies> allMoviesList;
    private List<Movies> searchedMoviesList;
    public int pageCount = 1;
    private boolean search = false;

    private boolean isLoading = false;
    private int offset = 0;
    private int rowCount = 20;


    public AllMoviesFragment() {
        // Required empty public constructor
    }


    public static AllMoviesFragment newInstance() {
        AllMoviesFragment fragment = new AllMoviesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_all_movies, container, false);
        ButterKnife.bind(this,view);
        init();
        onRecyclerScrolled();
        return view;
    }

    public void init(){
        allMoviesList = new ArrayList<>();
        moviesViewModel = ViewModelProviders.of(getActivity()).get(MoviesViewModel.class);
        viewModelDataProvider = new ViewModelDataProvider(getActivity(), moviesViewModel,this);
        staggeredGridLayoutManager = new GridLayoutManager(getActivity(),2);
        staggeredGridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        moviewRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        searchedEditText.addTextChangedListener(this);
        viewModelDataProvider.getMovies(pageCount);
        viewModelDataProvider.getAllMovi(offset,20);
        sharedPreferences = getActivity().getSharedPreferences("Movies",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Consts.isDataUpdated = false;

    }

    public void setDataOnRecyclerView(List<Movies> movies){
        if (pageCount == 1){
        allMoviesList.addAll(movies);
            moviesListAdapter = new MoviesListAdapter(getActivity(),movies,this, viewModelDataProvider);
            moviewRecyclerView.setAdapter(moviesListAdapter);
        }else {
            allMoviesList.addAll(movies);
            if (allMoviesList.size() > movies.size()) {
                moviesListAdapter.setPaginatedList(movies);
            }
        }
        moviesListAdapter.notifyDataSetChanged();
        isLoading = false;
    }

    public void setSearchedDataOnRecyclerView(List<Movies> movies){
        moviesListAdapter.setFilteredList(movies);
        moviesListAdapter.notifyDataSetChanged();
    }

    public void onRecyclerScrolled(){
        moviewRecyclerView.addOnScrollListener(new ScrollToPaginateListener(staggeredGridLayoutManager) {
            @Override
            protected void loadMore() {
                isLoading = true;
                Consts.pageCount++;
                offset = pageCount * 20;
                pageCount = Consts.pageCount;
                if (pageCount > sharedPreferences.getInt("pageCount", 1)) {
                    viewModelDataProvider.getMovies(pageCount);
                    editor.putInt("pageCount", pageCount).apply();
                    Consts.isDataUpdated = false;
                }else {
                    viewModelDataProvider.getAllMovi(offset, rowCount);
                }
            }

            @Override
            public boolean isLastPage() {
                return false;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public int getTotalPageCount() {
                return pageCount;
            }
        });
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
        if (search){
            setSearchedDataOnRecyclerView(movies);
        }else {
            if (movies.size() > 0) {
                setDataOnRecyclerView(movies);
            }
        }
    }

    @Override
    public void getMovie(Movies movies) {

    }

    @Override
    public void showFooter(boolean show) {
        if (show)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onrecyclerViewItemClicked(int position) {
        Intent intent = new Intent(getActivity(),MoviesDetailActivity.class);
        if (search){
            intent.putExtra(Consts.MOVIE_KEY, searchedMoviesList.get(position).getId());

        }else {
            intent.putExtra(Consts.MOVIE_KEY, allMoviesList.get(position).getId());
        }
        startActivity(intent);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() >= 3){
            search = true;
            searchedMoviesList = new ArrayList<>();
            viewModelDataProvider.getSearchedMoviesList(s.toString());
        }else {
            search = false;
            moviesListAdapter.setFilteredList(allMoviesList);
            moviesListAdapter.notifyDataSetChanged();
        }
    }
}
