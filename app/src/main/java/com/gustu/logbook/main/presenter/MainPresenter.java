package com.gustu.logbook.main.presenter;

import android.util.Log;

import com.gustu.logbook.main.interfaces.MainView;
import com.gustu.logbook.main.model.addLogbook.ResponseSaveLogbook;
import com.gustu.logbook.main.model.kegiatan.Kegiatan;
import com.gustu.logbook.main.model.levelKesulitan.Kesulitan;
import com.gustu.logbook.main.model.levelPrioritas.Priotitas;
import com.gustu.logbook.network.BaseURLJSON;
import com.gustu.logbook.network.BaseURLXML;
import com.gustu.logbook.sharePreferences.SharedPrefUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {
    MainView mainView;
    BaseURLJSON baseURLJSON;
    List<Kegiatan> kegiatanList = new ArrayList<>();
    List<Kesulitan> kesulitanList = new ArrayList<>();
    List<Priotitas> priotitasList = new ArrayList<>();
    BaseURLXML baseURLXML;
    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
        if (this.baseURLJSON == null) {
            this.baseURLJSON = new BaseURLJSON();
        }
        if (this.baseURLXML==null){
            this.baseURLXML = new BaseURLXML();
        }
    }

    public void getKegiatan(int key) {
        baseURLJSON.getAPI().getKegiatan(key).enqueue(new Callback<List<Kegiatan>>() {
            @Override
            public void onResponse(Call<List<Kegiatan>> call, Response<List<Kegiatan>> response) {
                if (response.isSuccessful()) {
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

    public void getPrioritas() {
        baseURLJSON.getAPI().getPrioritas().enqueue(new Callback<List<Priotitas>>() {
            @Override
            public void onResponse(Call<List<Priotitas>> call, Response<List<Priotitas>> response) {
                if (response.isSuccessful()) {
                    priotitasList = response.body();
                    mainView._onPrioritasLoad(priotitasList);
                }
            }

            @Override
            public void onFailure(Call<List<Priotitas>> call, Throwable t) {
                mainView._onFailed(t.toString());
            }
        });
    }

    public void getKesulitan() {
        baseURLJSON.getAPI().getKesulitan().enqueue(new Callback<List<Kesulitan>>() {
            @Override
            public void onResponse(Call<List<Kesulitan>> call, Response<List<Kesulitan>> response) {
                if (response.isSuccessful()) {
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

    public void saveLogbook(String id, String tanggalMulai, String tanggalAkhir, String kodeKegiatan, String namaKegiatan, String keteranganKegiatan, String outputLogbook, String tingkatKesulitan, String levelPrioritas, String jumlahKegiatan) {
        String kode_pegawai = SharedPrefUtil.getString("kode_pegawai");
        String namaPegawai = SharedPrefUtil.getString("nama");
        String kodeunit = SharedPrefUtil.getString("kode_unit");
        Log.d("HASIL PRESENTER", "saveLogbook: "+id+"\n"+tanggalMulai+"\n"+tanggalAkhir+"\n"+kode_pegawai+"\n"+namaPegawai+"\n"+kodeunit+"\n"+kodeKegiatan+"\n"+namaKegiatan+"\n"+keteranganKegiatan+"\n"+outputLogbook+"\n"+tingkatKesulitan+"\n"+levelPrioritas+"\n"+jumlahKegiatan+"\n"+2);
        baseURLXML.getAPI().saveLogbook(id, tanggalMulai, tanggalAkhir, kode_pegawai, namaPegawai, kodeunit, kodeKegiatan, namaKegiatan, keteranganKegiatan, outputLogbook, tingkatKesulitan, levelPrioritas, jumlahKegiatan, "2").enqueue(new Callback<ResponseSaveLogbook>() {
            @Override
            public void onResponse(Call<ResponseSaveLogbook> call, Response<ResponseSaveLogbook> response) {
                if (response.isSuccessful()) {
                    mainView._onDataAdd();
                    Log.d("SUCCESS SAVE LOG", "onResponse: "+response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseSaveLogbook> call, Throwable t) {
                mainView._onDataFailedAdd();
                Log.d("ERROR SAVE LOG", "onFailure: "+t.toString());
            }
        });
    }
}
