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
import com.example.myviewmodel.data.source.local.entity.TvEntity;
import com.example.myviewmodel.utils.Constant;
import com.example.myviewmodel.view.activity.DetailTvActivity;

import java.util.Objects;

public class FavTvAdapter extends PagedListAdapter<TvEntity, FavTvAdapter.ViewHolder> {
    final Activity activity;

    public FavTvAdapter(Activity activity) {
        super(DIFF_CALLBACK);
        this.activity = activity;
    }


    private static final DiffUtil.ItemCallback<TvEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<TvEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull TvEntity oldItem, @NonNull TvEntity newItem) {
                    return oldItem.getId() == (newItem.getId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull TvEntity oldItem, @NonNull TvEntity newItem) {
                    return Objects.equals(oldItem, newItem);
                }
            };

    @NonNull
    @Override
    public FavTvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_fave, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavTvAdapter.ViewHolder holder, int position) {
        TvEntity tv = getItem(position);
        if (tv != null) {
            holder.bindData(tv);
        }
        holder.linearLayout.setOnClickListener(v -> {
            Intent intent = new Intent(activity, DetailTvActivity.class);
            intent.putExtra(Constant.EXTRA_TV, tv);
            activity.startActivity(intent);
        });

    }

    public TvEntity getSwipedData(int swipedPosition) {
        return getItem(swipedPosition);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvOverview;
        final ImageView imgPoster;
        final LinearLayout linearLayout;
        final TextView rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            linearLayout = itemView.findViewById(R.id.container);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            imgPoster = itemView.findViewById(R.id.imgPoster);
            rating = itemView.findViewById(R.id.rating);

        }

        public void bindData(TvEntity tvResult) {
            tvTitle.setText(tvResult.getName());
            if (tvResult.getOverview().isEmpty()) tvOverview.setText(R.string.no_desc);
            else tvOverview.setText(tvResult.getOverview());
            Glide.with(itemView.getContext())
                    .load(Constant.TMDB_POSTER + tvResult.getPosterPath())
                    .into(imgPoster);
            rating.setText(String.valueOf(tvResult.getVoteAverage()));

        }
    }
}
