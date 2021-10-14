package com.example.myviewmodel.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myviewmodel.R;
import com.example.myviewmodel.adapter.MovieFragmentAdapter;
import com.example.myviewmodel.utils.Espresso;
import com.example.myviewmodel.viewModel.MovieViewModel;
import com.example.myviewmodel.viewModel.ViewModelFactory;
import com.facebook.shimmer.ShimmerFrameLayout;


public class MovieFragment extends Fragment {
    RecyclerView recyclerView;
    ShimmerFrameLayout shimmerFrameLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_tv, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rvMovieTv);
        MovieFragmentAdapter movieFragmentAdapter = new MovieFragmentAdapter(getActivity());
        shimmerFrameLayout = view.findViewById(R.id.shimmerContainer);
        shimmerFrameLayout.startShimmer();
        Espresso.increment();
        if (getActivity() != null) {
            ViewModelFactory factory = ViewModelFactory.getInstance(this.getActivity().getApplication());
            MovieViewModel movieViewModel = new ViewModelProvider(this, factory).get(MovieViewModel.class);
            movieViewModel.movieLiveData().observe(getViewLifecycleOwner(), movies -> {
                if (movies != null) {
                    switch (movies.status) {
                        case SUCCESS:
                            shimmerFrameLayout.setVisibility(View.INVISIBLE);
                            movieFragmentAdapter.submitList(movies.data);
                            break;
                        case LOADING:
                            break;
                    }

                }

            });
            setRecyclerView(movieFragmentAdapter);
            Espresso.decrement();

        }
    }

    private void setRecyclerView(MovieFragmentAdapter movieFragmentAdapter) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        recyclerView.smoothScrollToPosition(0);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(movieFragmentAdapter);
    }

}