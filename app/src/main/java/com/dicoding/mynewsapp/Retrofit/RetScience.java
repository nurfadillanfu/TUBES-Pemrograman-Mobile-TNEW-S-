package com.dicoding.mynewsapp.Retrofit;

import com.dicoding.mynewsapp.Business.BusinessList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetScience {
    String BASE_URL = "https://newsapi.org/v2/";

    @GET("top-headlines?country=id&category=science&apiKey=267aa85cedbb4866a7f98184c4d8150c")
    Call<BusinessList> getBusiness();

}