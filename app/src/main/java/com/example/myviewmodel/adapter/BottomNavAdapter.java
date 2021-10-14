package com.example.myviewmodel.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myviewmodel.view.fragment.MovieFragment;
import com.example.myviewmodel.view.fragment.TvFragment;

public class BottomNavAdapter extends FragmentStateAdapter {
    public BottomNavAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new MovieFragment();
            case 1:
                return new TvFragment();
            default:
                throw new IllegalStateException("Invalid Fragment Position");
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
