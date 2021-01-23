package com.dicoding.mynewsapp.Fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dicoding.mynewsapp.Adapter.ViewPagerAdapter;
import com.dicoding.mynewsapp.Category.Business;
import com.dicoding.mynewsapp.Category.Entertaiment;
import com.dicoding.mynewsapp.Category.Health;
import com.dicoding.mynewsapp.Category.Science;
import com.dicoding.mynewsapp.Category.Sport;
import com.dicoding.mynewsapp.Category.Technology;
import com.dicoding.mynewsapp.R;

public class NewsFragment extends Fragment {

//
//    public FavoriteFragment() {
//
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_news, container, false);
        ViewPager viewPager = view.findViewById(R.id.viewpager);
        TabLayout tabLayout = view.findViewById(R.id.tabs);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter viewPageAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPageAdapter.addFragment(new Business(), "BISNIS");
        viewPageAdapter.addFragment(new Entertaiment(), "ENTERTAIMENT");
        viewPageAdapter.addFragment(new Health(), "KESEHATAN");
        viewPageAdapter.addFragment(new Science(), "SAINS");
        viewPageAdapter.addFragment(new Sport(), "OLAHRAGA");
        viewPageAdapter.addFragment(new Technology(), "TEKNOLOGI");
        viewPager.setAdapter(viewPageAdapter);
    }

}
