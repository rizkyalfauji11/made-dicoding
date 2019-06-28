package com.code.alpha.alphamade.submission.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;


import com.code.alpha.alphamade.R;
import com.code.alpha.alphamade.submission.adapter.MadeViewPagerAdapter;
import com.code.alpha.alphamade.submission.fragment.movielist.MovieListFragment;
import com.code.alpha.alphamade.submission.model.Constant;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {

    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setSubtitle(getResources().getString(R.string.recommended_movies));
        toolbar.setSubtitleTextColor(getResources().getColor(android.R.color.white));


        TabLayout tabMenu = findViewById(R.id.tab_menu);
        ViewPager viewPager = findViewById(R.id.view_pager);
        MadeViewPagerAdapter tabAdapter = new MadeViewPagerAdapter(getSupportFragmentManager(), 1);
        tabAdapter.addFragment(
                MovieListFragment.newInstance(
                        R.raw.movies
                ), getResources().getString(R.string.string_movie));
        tabAdapter.addFragment(
                MovieListFragment.newInstance(
                        R.raw.tvshow
                ), getResources().getString(R.string.string_tv_show));
        viewPager.setAdapter(tabAdapter);
        tabMenu.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_change_settings){
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void addFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_container, fragment, fragment.getClass().getSimpleName());
        fragmentTransaction.commit();
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment, fragment.getClass().getSimpleName());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
