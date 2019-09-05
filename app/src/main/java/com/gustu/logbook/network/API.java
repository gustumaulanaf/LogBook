package com.gustu.logbook.network;

import com.gustu.logbook.main.model.addLogbook.ResponseSaveLogbook;
import com.gustu.logbook.main.model.kegiatan.Kegiatan;
import com.gustu.logbook.main.model.levelKesulitan.Kesulitan;
import com.gustu.logbook.main.model.levelPrioritas.Priotitas;
import com.gustu.logbook.fragment.home.model.LogbookResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {
    //Kegiatan
    @Headers("Accept:application/json")
    @POST("getKegiatan")
     @FormUrlEncoded
    Call<List<Kegiatan>> getKegiatan(
            @Field("key") int key
    );

    //Level Prioritas
    @Headers("Accept:application/json")
    @GET("getLevelPrioritas")
    Call<List<Priotitas>> getPrioritas();

    //Level Kesulitan
    @Headers("Accept:application/json")
    @GET("getLevelKesulitan")
    Call<List<Kesulitan>> getKesulitan();

    //saveLogbook
    @Headers("Accept: application/xml")
    @POST("saveLogbook")
    @FormUrlEncoded
    Call<ResponseSaveLogbook> saveLogbook(
            @Field("id_logbook") String id,
            @Field("tanggal_awal") String tangggalAwal,
            @Field("tanggal_ahir") String tanggalAkhir,
            @Field("kode_pegawai") String kodePegawai,
            @Field("nama_pegawai") String namaPegawai,
            @Field("kode_unit") String kodeUnit,
            @Field("kode_kegiatan") String kodeKegiatan,
            @Field("nama_kegiatan") String namaKegiatan,
            @Field("keterangan_logbook") String keteranganLogBook,
            @Field("output_logbook") String outputLogbook,
            @Field("level_kesulitan") String levelKesulitan,
            @Field("level_prioritas") String levelPrioritas,
            @Field("jumlah_kegiatan") String jumlahKegiatan,
            @Field("jenis_source_data") String jenisSourceData
            );

    //GET LOGBOOK
    @Headers("Accept:application/json")
    @POST("getLogBook")
    @FormUrlEncoded
    Call<LogbookResponse> getLogbook(
            @Field("key") String key
    );
}
