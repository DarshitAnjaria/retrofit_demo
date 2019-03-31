package com.android.retrofitdemo;

import com.android.retrofitdemo.model.RetroData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("books")
    Call<List<RetroData>> getAllData();
}
