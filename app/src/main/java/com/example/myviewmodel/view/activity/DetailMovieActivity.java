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
import com.example.myviewmodel.data.source.local.entity.MovieEntity;
import com.example.myviewmodel.utils.Constant;
import com.example.myviewmodel.viewModel.DetailViewModel;
import com.example.myviewmodel.viewModel.ViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class DetailMovieActivity extends AppCompatActivity {
    ImageView imgBackdrop;
    TextView tvTitle;
    TextView tvDesc;
    MovieEntity selectedMovie;
    ImageView circleImageView;
    TextView tvReleaseDate;
    RatingBar ratingBar;
    FloatingActionButton fabFav;
    DetailViewModel detailViewModel;
    TextView rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imgBackdrop = findViewById(R.id.detailPoster);
        circleImageView = findViewById(R.id.detailCircleImage);
        tvReleaseDate = findViewById(R.id.dateRelease);
        tvTitle = findViewById(R.id.tvTitle);
        tvDesc = findViewById(R.id.detailDesc);
        ratingBar = findViewById(R.id.ratingBar);
        fabFav = findViewById(R.id.fabFav);
        rating = findViewById(R.id.rating);
        detailViewModel = obtainViewModel();
        selectedMovie = getIntent().getParcelableExtra(Constant.EXTRA_MOVIE);
        detailViewModel.setIdMovie(selectedMovie.getId());
        if (selectedMovie.getId() > 0) {
            detailViewModel.getSelectedMovie().observe(this, movieEntityResource -> {
                if (movieEntityResource != null) {
                    switch (movieEntityResource.status) {
                        case SUCCESS:
                            if (movieEntityResource.data != null) {
                                populateMovie(selectedMovie);
                                break;
                            }
                        case ERROR:
                            Log.d("DetailMovieActivity", "onCreate: Error bre");
                            break;
                    }
                }
            });
            setFave(selectedMovie);

        }
    }

    private DetailViewModel obtainViewModel() {
        ViewModelFactory factory = ViewModelFactory.getInstance(this.getApplication());
        return detailViewModel = new ViewModelProvider(this, factory).get(DetailViewModel.class);
    }

    private void setFave(MovieEntity selectedMovie) {
        if (selectedMovie.isFave()) {
            fabFav.setImageResource(R.drawable.fab_on);
        }
        detailViewModel.getSelectedMovie().observe(this, faveMovie -> {
            if (faveMovie != null) {
                switch (faveMovie.status) {
                    case SUCCESS:
                        if (faveMovie.data != null) {
                            boolean state = faveMovie.data.isFave();
                            fabFav.setOnClickListener(v -> {
                                if (state) {
                                    fabFav.setImageResource(R.drawable.fab_off);
                                    detailViewModel.setFaveMovie();
                                    Log.d("TAG", "setFave: delete fav");
                                    Snackbar.make(ratingBar, "Deleted from favourites", Snackbar.LENGTH_LONG).show();

                                } else {
                                    fabFav.setImageResource(R.drawable.fab_on);
                                    detailViewModel.setFaveMovie();
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


    private void populateMovie(MovieEntity data) {
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(this);
        circularProgressDrawable.setStyle(LARGE);
        circularProgressDrawable.setColorSchemeColors(ContextCompat.getColor(this, R.color.red_400));
        circularProgressDrawable.start();
        Glide.with(this)
                .load(Constant.TMDB_BACKDROP + data.getBackdropPath())
                .placeholder(circularProgressDrawable)
                .into(imgBackdrop);
        tvTitle.setText(data.getTitle());
        ratingBar.setRating(data.getVoteAverage() / 2);
        tvReleaseDate.setText(data.getReleaseDate());
        Glide.with(this)
                .load(Constant.TMDB_POSTER + data.getPosterPath())
                .into(circleImageView);
        if (data.getOverview() != null) {
            tvDesc.setText(data.getOverview());
        } else tvDesc.setText(R.string.no_desc);
        rating.setText(getString(R.string.rating,data.getVoteAverage()));
    }
}