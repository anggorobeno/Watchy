package com.example.myviewmodel.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myviewmodel.R;
import com.example.myviewmodel.adapter.FavTvAdapter;
import com.example.myviewmodel.data.source.local.entity.TvEntity;
import com.example.myviewmodel.viewModel.FaveViewModel;
import com.example.myviewmodel.viewModel.ViewModelFactory;
import com.google.android.material.snackbar.Snackbar;


public class FaveTvFragment extends Fragment {
    RecyclerView recyclerView;
    FavTvAdapter movieFragmentAdapter;
    FaveViewModel faveViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fave_tv, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rvFaveTv);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        movieFragmentAdapter = new FavTvAdapter(getActivity());
        if (getActivity() != null) {
            ViewModelFactory factory = ViewModelFactory.getInstance(this.getActivity().getApplication());
            faveViewModel = new ViewModelProvider(this, factory).get(FaveViewModel.class);
            faveViewModel.getFaveTv().observe(getViewLifecycleOwner(), movies -> {
                if (movies != null) {
                    movieFragmentAdapter.submitList(movies);
                    setRecyclerView(movieFragmentAdapter);
                    movieFragmentAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    private final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {

        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            if (getView() != null) {
                int swipedPosition = viewHolder.getAdapterPosition();
                TvEntity tvEntity = movieFragmentAdapter.getSwipedData(swipedPosition);
                faveViewModel.setFaveTvPaged(tvEntity);
                Snackbar snackbar = Snackbar.make(getView(), R.string.message_undo, Snackbar.LENGTH_LONG);
                snackbar.setAction(R.string.message_ok, v -> faveViewModel.setFaveTvPaged(tvEntity));
                snackbar.show();
            }
        }
    });

    private void setRecyclerView(FavTvAdapter movieFragmentAdapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.smoothScrollToPosition(0);
        recyclerView.setAdapter(movieFragmentAdapter);
    }
}