package com.example.myviewmodel.view.activity;

import android.os.Bundle;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myviewmodel.R;
import com.example.myviewmodel.adapter.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class FaveActivity extends AppCompatActivity {
    private TabLayout tabs;
    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.title_movie,
            R.string.title_tv
    };
    private final int[] tabIcons = {
            R.drawable.movie_icon,
            R.drawable.tv_icon
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fave);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this);
        ViewPager2 viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs = findViewById(R.id.tabs);
        new TabLayoutMediator(tabs, viewPager,
                (tab, position) -> tab.setText(getResources().getString(TAB_TITLES[position]))
        ).attach();
        setupIcons();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }
    }


    private void setupIcons() {
        tabs.getTabAt(0).setIcon(tabIcons[0]);
        tabs.getTabAt(1).setIcon(tabIcons[1]);


    }
}
