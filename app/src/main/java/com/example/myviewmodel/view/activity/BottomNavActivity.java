package com.example.myviewmodel.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myviewmodel.R;
import com.example.myviewmodel.adapter.BottomNavAdapter;
import com.example.myviewmodel.databinding.ActivityBottomNavBinding;

public class BottomNavActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBottomNavBinding activityBottomNavBinding = ActivityBottomNavBinding.inflate(getLayoutInflater());
        setContentView(activityBottomNavBinding.getRoot());
        BottomNavAdapter bottomNavAdapter = new BottomNavAdapter(this);
        activityBottomNavBinding.viewPager.setAdapter(bottomNavAdapter);
        activityBottomNavBinding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                activityBottomNavBinding.bubbleTabBar.setSelected(position, false);
            }
        });
        activityBottomNavBinding.bubbleTabBar.addBubbleListener(id -> {
            if (id == R.id.menu_movie) {
                activityBottomNavBinding.viewPager.setCurrentItem(0);
            } else if (id == R.id.menu_tv) {
                activityBottomNavBinding.viewPager.setCurrentItem(1);
            }
        });
        setSupportActionBar(activityBottomNavBinding.toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.toolbarFave) {
            Intent intent = new Intent(this, FaveActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




}