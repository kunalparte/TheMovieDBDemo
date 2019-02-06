package com.example.kunalparte.themoviedbtask.movies.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kunalparte.themoviedbtask.R;
import com.example.kunalparte.themoviedbtask.movies.db.ViewModelDataProvider;
import com.example.kunalparte.themoviedbtask.movies.interfaces.RecyclerItemClicked;
import com.example.kunalparte.themoviedbtask.movies.models.Movies;
import com.example.kunalparte.themoviedbtask.utils.Consts;
import com.example.kunalparte.themoviedbtask.utils.GlideLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.VHolder> {

    private Activity activity;
    private List<Movies> movies;
    private RecyclerItemClicked recyclerItemClicked;
    private ViewModelDataProvider viewModelDataProvider;

    private int previousPosition = 0;
    private boolean isBookmarked = false;

    public MoviesListAdapter(Activity activity, List<Movies> movies, RecyclerItemClicked recyclerItemClicked, ViewModelDataProvider viewModelDataProvider){
        this.movies = movies;
        this.activity = activity;
        this.recyclerItemClicked = recyclerItemClicked;
        this.viewModelDataProvider = viewModelDataProvider;
    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(activity).inflate(R.layout.movies_list_item, viewGroup,false);
        return new VHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VHolder vHolder, final int i) {
        vHolder.positionClicked = i;

        vHolder.titleTextView.setText(movies.get(i).getTitle());
        vHolder.dateTextView.setText(movies.get(i).getRelease_date());

        GlideLoader
                .url(activity)
                .load(Consts.IMAGE_BASE_URL + movies.get(i).getPoster_path()).into(vHolder.moviesImageView);


        if (movies.get(i).isBookmarked()){
            vHolder.bookmarkButton.setSelected(true);
        }else{
            vHolder.bookmarkButton.setSelected(false);
        }

        vHolder.bookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movies movie = movies.get(i);
                if (vHolder.bookmarkButton.isSelected()){
                    movie.setBookmarked(false);
                    vHolder.bookmarkButton.setSelected(false);
                }else {
                    movie.setBookmarked(true);
                    vHolder.bookmarkButton.setSelected(false);
                }
                viewModelDataProvider.updateMovie(movie);
            }
        });


    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setFilteredList(List<Movies> moviesList){
        movies = new ArrayList<>(moviesList);
    }

    public void setPaginatedList(List<Movies> movies){
        movies.addAll(movies);
    }

    public class VHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.moviesItemImageView)
        ImageView moviesImageView;

        @BindView(R.id.bookmarkButton)
        ImageButton bookmarkButton;

        @BindView(R.id.titleTextView)
        TextView titleTextView;

        @BindView(R.id.movieDateTextView)
        TextView dateTextView;

        int positionClicked = 0;

        public VHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            recyclerItemClicked.onrecyclerViewItemClicked(positionClicked);
        }
    }
}
