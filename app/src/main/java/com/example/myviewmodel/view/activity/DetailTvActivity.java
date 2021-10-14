package com.example.myviewmodel.view.activity;

import static androidx.swiperefreshlayout.widget.CircularProgressDrawable.LARGE;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.example.myviewmodel.R;
import com.example.myviewmodel.data.source.local.entity.TvEntity;
import com.example.myviewmodel.utils.Constant;
import com.example.myviewmodel.viewModel.DetailViewModel;
import com.example.myviewmodel.viewModel.ViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class DetailTvActivity extends AppCompatActivity {
    ImageView imgBackdrop;
    TextView tvTitle;
    TextView tvDesc;
    TvEntity selectedTv;
    ImageView circleImageView;
    TextView tvAiringDate;
    FloatingActionButton fabFav;
    DetailViewModel detailViewModel;
    RatingBar ratingBar;
    TextView date;
    TextView rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imgBackdrop = findViewById(R.id.detailPoster);
        circleImageView = findViewById(R.id.detailCircleImage);
        tvAiringDate = findViewById(R.id.dateRelease);
        date = findViewById(R.id.textView3);
        imgBackdrop = findViewById(R.id.detailPoster);
        ratingBar = findViewById(R.id.ratingBar);
        fabFav = findViewById(R.id.fabFav);
        tvTitle = findViewById(R.id.tvTitle);
        detailViewModel = obtainViewModel();
        tvDesc = findViewById(R.id.detailDesc);
        rating = findViewById(R.id.rating);
        selectedTv = getIntent().getParcelableExtra(Constant.EXTRA_TV);
        ViewModelFactory factory = ViewModelFactory.getInstance(getApplication());
        DetailViewModel detailViewModel = new ViewModelProvider(this, factory).get(DetailViewModel.class);
        detailViewModel.setIdTv(selectedTv.getId());
        if (selectedTv.getId() > 0) {
            detailViewModel.getSelectedTv().observe(this, tvResult -> {
                if (tvResult != null) {
                    switch (tvResult.status) {
                        case SUCCESS:
                            if (tvResult.data != null) {
                                populateMovie(selectedTv);
                                break;
                            }
                        case ERROR:
                            Log.d("DetailMovieActivity", "onCreate: Error bre");
                            break;
                    }
                }

            });
            setFave(selectedTv);

        }

    }

    private void setFave(TvEntity selectedTv) {
        if (selectedTv.isFave()) {
            fabFav.setImageResource(R.drawable.fab_on);
        }
        detailViewModel.getSelectedTv().observe(this, faveTv -> {
            if (faveTv != null) {
                switch (faveTv.status) {
                    case SUCCESS:
                        if (faveTv.data != null) {
                            boolean state = faveTv.data.isFave();
                            fabFav.setOnClickListener(v -> {
                                if (state) {
                                    fabFav.setImageResource(R.drawable.fab_off);
                                    detailViewModel.setFaveTv();
                                    Log.d("TAG", "setFave: delete fav");
                                    Snackbar.make(ratingBar, "Deleted from favourites", Snackbar.LENGTH_LONG).show();

                                } else {
                                    fabFav.setImageResource(R.drawable.fab_on);
                                    detailViewModel.setFaveTv();
                                    Log.d("TAG", "setFave: add fav");
                                    Snackbar.make(ratingBar, "Add to favourites", Snackbar.LENGTH_LONG).show();
                                }
                            });
                        }
                        break;
                    case ERROR:
                        Log.d("DetailMovieActivity", "onCreate: Error bre");
                }
            }
        });
    }

    private void populateMovie(TvEntity selectedTv) {
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(this);
        circularProgressDrawable.setStyle(LARGE);
        circularProgressDrawable.setColorSchemeColors(ContextCompat.getColor(this, R.color.red_400));
        circularProgressDrawable.start();
        Glide.with(this)
                .load(Constant.TMDB_BACKDROP + selectedTv.getBackdropPath())
                .placeholder(circularProgressDrawable)
                .into(imgBackdrop);
        tvTitle.setText(selectedTv.getName());
        tvDesc.setText(selectedTv.getOverview());
        ratingBar.setRating(selectedTv.getVoteAverage() / 2);
        tvAiringDate.setText(selectedTv.getFirstAirDate());
        date.setText(R.string.airing_date);
        Glide.with(this)
                .load(Constant.TMDB_POSTER + selectedTv.getPosterPath())
                .into(circleImageView);
        if (selectedTv.getOverview().isEmpty()) {
            tvDesc.setText(R.string.no_desc);
        } else tvDesc.setText(selectedTv.getOverview());
        rating.setText(getString(R.string.rating,selectedTv.getVoteAverage()));

    }

    private DetailViewModel obtainViewModel() {
        ViewModelFactory factory = ViewModelFactory.getInstance(this.getApplication());
        return detailViewModel = new ViewModelProvider(this, factory).get(DetailViewModel.class);
    }
}
