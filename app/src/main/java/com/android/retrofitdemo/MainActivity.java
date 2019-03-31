package com.android.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.retrofitdemo.adapter.CustomAdapter;
import com.android.retrofitdemo.model.RetroData;
import com.android.retrofitdemo.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomAdapter adapter;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<RetroData>> call = getDataService.getAllData();

        call.enqueue(new Callback<List<RetroData>>() {
            @Override
            public void onResponse(Call<List<RetroData>> call, Response<List<RetroData>> response) {
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<RetroData>> call, Throwable t) {

            }
        });
    }

    private void generateDataList(List<RetroData> dataList){
        recyclerView = findViewById(R.id.recyclerViewBooks);
        adapter = new CustomAdapter(dataList,MainActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}
