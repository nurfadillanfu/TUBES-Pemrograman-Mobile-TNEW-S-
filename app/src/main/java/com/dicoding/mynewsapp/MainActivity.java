package com.dicoding.mynewsapp;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.dicoding.mynewsapp.Fragment.FavoriteFragment;
import com.dicoding.mynewsapp.Fragment.NewsFragment;
import com.dicoding.mynewsapp.db.BookmarkHelper;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    private ViewPager viewPager;
    BookmarkHelper bookmarkHelper;
//    private FavoriteHelper favoriteHelper;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.nav_news:
                    fragment = new NewsFragment();
                    setupFragment(fragment);
                    return true;
                case R.id.nav_save:
                    fragment = new FavoriteFragment();
                    setupFragment(fragment);
                    return true;

            }
            return false;
        }
    };




    private void fullScreen(){
        View decorView = getWindow().getDecorView();
// Hide both the navigation bar and the status bar.
// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
// a general rule, you should design your app to hide the status bar whenever you
// hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    private void setupFragment (Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_fragment, fragment, fragment.getClass().getSimpleName())
                .commit();
    }
}

