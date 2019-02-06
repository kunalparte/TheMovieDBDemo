package com.example.kunalparte.themoviedbtask.movies.adapters;

import android.app.Activity;
import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kunalparte.themoviedbtask.R;
import com.example.kunalparte.themoviedbtask.movies.db.ViewModelDataProvider;
import com.example.kunalparte.themoviedbtask.movies.models.Movies;
import com.example.kunalparte.themoviedbtask.utils.Consts;
import com.example.kunalparte.themoviedbtask.utils.GlideLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesPageListAdapter extends PagedListAdapter<Movies,MoviesPageListAdapter.Vholder> {

    private Context activity;
    private ViewModelDataProvider viewModelDataProviderl;

    public static DiffUtil.ItemCallback<Movies> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Movies>() {
                @Override
                public boolean areItemsTheSame(@NonNull Movies movies, @NonNull Movies t1) {
                    return movies.getId() == t1.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull Movies movies, @NonNull Movies t1) {
                    return movies.equals(t1);
                }
            };

    public MoviesPageListAdapter( Context context,ViewModelDataProvider viewModelDataProvider) {
        super(DIFF_CALLBACK);
        this.activity = context;
        this.viewModelDataProviderl = viewModelDataProvider;
    }

//    protected MoviesPageListAdapter(@NonNull AsyncDifferConfig<Movies> config) {
//        super(config);
//    }

    @NonNull
    @Override
    public Vholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(activity).inflate(R.layout.movies_list_item, viewGroup,false);
        return new MoviesPageListAdapter.Vholder(view);    }

    @Override
    public void onBindViewHolder(@NonNull Vholder vHolder, int i) {
//        vHolder.positionClicked = i;
        Movies movies = getItem(i);
        vHolder.titleTextView.setText(movies.getTitle());
        vHolder.dateTextView.setText(movies.getRelease_date());

        GlideLoader
                .url(activity)
                .load(Consts.IMAGE_BASE_URL + movies.getPoster_path()).into(vHolder.moviesImageView);


        if (movies.isBookmarked()){
            vHolder.bookmarkButton.setSelected(true);
        }else{
            vHolder.bookmarkButton.setSelected(false);
        }

        vHolder.bookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vHolder.bookmarkButton.isSelected()){
                    movies.setBookmarked(false);
                    vHolder.bookmarkButton.setSelected(false);
                }else {
                    movies.setBookmarked(true);
                    vHolder.bookmarkButton.setSelected(false);
                }
                viewModelDataProviderl.updateMovie(movies);
            }
        });
    }


    public class Vholder extends RecyclerView.ViewHolder {
        @BindView(R.id.moviesItemImageView)
        ImageView moviesImageView;

        @BindView(R.id.bookmarkButton)
        ImageButton bookmarkButton;

        @BindView(R.id.titleTextView)
        TextView titleTextView;

        @BindView(R.id.movieDateTextView)
        TextView dateTextView;

        public Vholder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }



    }
}
