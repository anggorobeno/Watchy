package com.example.myviewmodel.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myviewmodel.R;
import com.example.myviewmodel.data.source.local.entity.MovieEntity;
import com.example.myviewmodel.utils.Constant;
import com.example.myviewmodel.view.activity.DetailMovieActivity;

import java.util.Objects;

public class FavMovieAdapter extends PagedListAdapter<MovieEntity, FavMovieAdapter.ViewHolder> {
    final Activity activity;

    public FavMovieAdapter(Activity activity) {
        super(DIFF_CALLBACK);
        this.activity = activity;
    }

    private static final DiffUtil.ItemCallback<MovieEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MovieEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
                    return oldItem.getId() == (newItem.getId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
                    return Objects.equals(oldItem, newItem);
                }
            };

    @NonNull
    @Override
    public FavMovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_fave, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavMovieAdapter.ViewHolder holder, int position) {
        MovieEntity movies = getItem(position);
        if (movies != null) {
            holder.bindData(movies);
        }
        holder.linearLayout.setOnClickListener(v -> {
            Intent intent = new Intent(activity, DetailMovieActivity.class);
            intent.putExtra(Constant.EXTRA_MOVIE, movies);
            activity.startActivity(intent);

        });
    }

    public MovieEntity getSwipedData(int swipedPosition) {
        return getItem(swipedPosition);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final ImageView imgPoster;
        final TextView tvOverview;
        final LinearLayout linearLayout;
        final TextView rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            imgPoster = itemView.findViewById(R.id.imgPoster);
            linearLayout = itemView.findViewById(R.id.container);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            rating = itemView.findViewById(R.id.rating);
        }

        public void bindData(MovieEntity movieEntity) {
            tvTitle.setText(movieEntity.getTitle());
            if (movieEntity.getOverview().isEmpty()) tvOverview.setText(R.string.no_desc);
            else tvOverview.setText(movieEntity.getOverview());
            Glide.with(itemView.getContext())
                    .load(Constant.TMDB_POSTER + movieEntity.getPosterPath())
                    .into(imgPoster);
            rating.setText(String.valueOf(movieEntity.getVoteAverage()));
        }
    }
}
