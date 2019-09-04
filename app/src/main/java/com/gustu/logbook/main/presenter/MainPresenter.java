package com.gustu.logbook.main.presenter;

import com.gustu.logbook.main.interfaces.MainView;
import com.gustu.logbook.main.model.addLogbook.ResponseSaveLogbook;
import com.gustu.logbook.main.model.kegiatan.Kegiatan;
import com.gustu.logbook.main.model.levelKesulitan.Kesulitan;
import com.gustu.logbook.main.model.levelPrioritas.Priotitas;
import com.gustu.logbook.network.BaseURL;
import com.gustu.logbook.sharePreferences.SharedPrefUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {
    MainView mainView;
    BaseURL baseURL;
    List<Kegiatan> kegiatanList = new ArrayList<>();
    List<Kesulitan> kesulitanList = new ArrayList<>();
    List<Priotitas> priotitasList = new ArrayList<>();
    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
        if (this.baseURL==null){
            this.baseURL = new BaseURL();
        }
    }
    public  void getKegiatan(int key){
        baseURL.getAPI().getKegiatan(key).enqueue(new Callback<List<Kegiatan>>() {
            @Override
            public void onResponse(Call<List<Kegiatan>> call, Response<List<Kegiatan>> response) {
                if (response.isSuccessful()){
                    kegiatanList = response.body();
                    mainView._onKegiatanLoad(kegiatanList);
                }
            }

            @Override
            public void onFailure(Call<List<Kegiatan>> call, Throwable t) {
                    mainView._onFailed(t.toString());
            }
        });
    }
    public void getPrioritas(){
        baseURL.getAPI().getPrioritas().enqueue(new Callback<List<Priotitas>>() {
            @Override
            public void onResponse(Call<List<Priotitas>> call, Response<List<Priotitas>> response) {
                if (response.isSuccessful()){
                    priotitasList= response.body();
                    mainView._onPrioritasLoad(priotitasList);
                }
            }

            @Override
            public void onFailure(Call<List<Priotitas>> call, Throwable t) {
                    mainView._onFailed(t.toString());
            }
        });
    }
    public void  getKesulitan(){
        baseURL.getAPI().getKesulitan().enqueue(new Callback<List<Kesulitan>>() {
            @Override
            public void onResponse(Call<List<Kesulitan>> call, Response<List<Kesulitan>> response) {
                if (response.isSuccessful()){
                    kesulitanList = response.body();
                    mainView._onKesulitanLoad(kesulitanList);
                }
            }

            @Override
            public void onFailure(Call<List<Kesulitan>> call, Throwable t) {
                    mainView._onFailed(t.toString());
            }
        });
    }
        public void saveLogbook (String id , String tanggalMulai,String tanggalAkhir,String kodeKegiatan,String namaKegiatan,String keteranganKegiatan,String outputLogbook , String tingkatKesulitan ,String levelPrioritas,String jumlahKegiatan){
            String kode_pegawai = SharedPrefUtil.getString("kode_pegawai");
            String namaPegawai = SharedPrefUtil.getString("nama");
            String kodeunit = SharedPrefUtil.getString("kode_unit");
            baseURL.getAPI().saveLogbook(id,tanggalMulai,tanggalAkhir, kode_pegawai,namaPegawai,kodeunit,kodeKegiatan,namaKegiatan,keteranganKegiatan,outputLogbook,tingkatKesulitan,levelPrioritas,jumlahKegiatan,"2").enqueue(new Callback<ResponseSaveLogbook>() {
                @Override
                public void onResponse(Call<ResponseSaveLogbook> call, Response<ResponseSaveLogbook> response) {
                    if (response.isSuccessful()){
                        mainView._onDataAdd();
                    }
                }

                @Override
                public void onFailure(Call<ResponseSaveLogbook> call, Throwable t) {
                        mainView._onDataFailedAdd();
                }
            });
        }
}
