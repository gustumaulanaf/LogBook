package com.gustu.logbook.fragment.home.presenter;

import android.util.Log;

import com.gustu.logbook.fragment.home.interfaces.HomeView;
import com.gustu.logbook.fragment.home.model.DataItem;
import com.gustu.logbook.fragment.home.model.LogbookResponse;
import com.gustu.logbook.network.BaseURLJSON;
import com.gustu.logbook.network.BaseURLXML;
import com.gustu.logbook.sharePreferences.SharedPrefUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter {
HomeView homeView;
BaseURLJSON baseURLJSON;
final String TAG ="HOME PRESENTER";
List<DataItem> dataItemList = new ArrayList<>();
    public HomePresenter(HomeView homeView) {
        this.homeView = homeView;
        if (this.baseURLJSON ==null){
            this.baseURLJSON =new BaseURLJSON();
        }

    }
    public void getLogbook(){
        String key = SharedPrefUtil.getString("kode_pegawai");
        baseURLJSON.getAPI().getLogbook(key).enqueue(new Callback<LogbookResponse>() {
            @Override
            public void onResponse(Call<LogbookResponse> call, Response<LogbookResponse> response) {
                if (response.isSuccessful()){
                    Log.d("HOME PRESENTER", "onResponse: "+response.code());
                    LogbookResponse logbookResponse = response.body();
                    dataItemList = logbookResponse.getData();
                    homeView._onDataLoad(dataItemList);
                }
            }

            @Override
            public void onFailure(Call<LogbookResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());
                    homeView._onDataFail();
            }
        });
    }
}
