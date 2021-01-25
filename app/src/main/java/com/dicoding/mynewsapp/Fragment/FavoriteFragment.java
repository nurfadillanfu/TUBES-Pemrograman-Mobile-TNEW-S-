package com.dicoding.mynewsapp.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dicoding.mynewsapp.Adapter.BusinessAdapter;
import com.dicoding.mynewsapp.Adapter.ViewPagerAdapter;
import com.dicoding.mynewsapp.R;
import com.dicoding.mynewsapp.db.BookmarkHelper;

public class FavoriteFragment extends Fragment {
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_bookmark,container,false);

        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.rv_news);

        showLoading(true);
        setRecycleView();
        return view;
    }

    private void setRecycleView() {
        BusinessAdapter businessAdapter = new BusinessAdapter(getContext());
        businessAdapter.notifyDataSetChanged();
        BookmarkHelper bookmarkHelper = BookmarkHelper.getInstance(getContext());
        businessAdapter.setListBusiness(bookmarkHelper.getAllNews());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(businessAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        showLoading(false);
    }

    private void showLoading(Boolean state) {
        if (state){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
