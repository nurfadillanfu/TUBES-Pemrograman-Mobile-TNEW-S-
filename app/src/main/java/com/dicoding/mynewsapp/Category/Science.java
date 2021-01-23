package com.dicoding.mynewsapp.Category;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dicoding.mynewsapp.Adapter.BusinessAdapter;
import com.dicoding.mynewsapp.Business.Article;
import com.dicoding.mynewsapp.Business.BusinessList;
import com.dicoding.mynewsapp.R;
import com.dicoding.mynewsapp.Retrofit.RetEntertaiment;
import com.dicoding.mynewsapp.Retrofit.RetScience;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Science extends Fragment{
    private RecyclerView rvMovies;
    private BusinessAdapter adapter;
    private ArrayList<Article> listMv = new ArrayList<Article>();

    private ProgressBar loading;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_business, container, false);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovies = (RecyclerView) view.findViewById(R.id.rv_news);

        loading = view.findViewById(R.id.progressBar);
        if (savedInstanceState!= null && savedInstanceState.getSerializable("save")!=null){
            listMv = new Gson().fromJson(savedInstanceState.getSerializable("save").toString(),new TypeToken<ArrayList<Article>>(){}.getType());

            Log.e("adakah", listMv.get(0).getTitle());
            setListFilm(listMv);
            loading.setVisibility(View.GONE);
        }
        else {
            addItem();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
        Log.e("saving data duluan", String.valueOf(listMv));
        outstate.putSerializable("save",new Gson().toJson(listMv));
    }



    //    @Override
//    public void onCreate(@NonNull Bundle savedInstanceState) {
    private void addItem() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetScience.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetScience retrofitnya = retrofit.create(RetScience.class);
        Call<BusinessList> hubungi = retrofitnya.getBusiness();
        hubungi.enqueue(new Callback<BusinessList>() {
            @Override
            public void onResponse(Call<BusinessList> call, Response<BusinessList> response) {
                BusinessList tv = response.body();
                Log.e("Get data", "duluan");

                for (int a = 0; a < tv.getArticles().size(); a++){
                    listMv.add(tv.getArticles().get(a));
                }
                loading.setVisibility(View.GONE);
                setListFilm(listMv);
            }

            @Override
            public void onFailure(Call<BusinessList> call, Throwable t) {
                Toast.makeText(getActivity(),  t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void setListFilm(ArrayList<Article> list){

//        for (int a = 0; a < list.size(); a++){
//            Log.e("saat di set", list.get(a).getPosterPath() + "ada");
//        }
        rvMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
        BusinessAdapter go = new BusinessAdapter(list);
        rvMovies.setAdapter(go);
        rvMovies.setNestedScrollingEnabled(false);
    }

}

