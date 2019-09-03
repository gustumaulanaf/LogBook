package com.gustu.logbook.network;

import com.gustu.logbook.models.kegiatan.Kegiatan;
import com.gustu.logbook.models.levelKesulitan.Kesulitan;
import com.gustu.logbook.models.levelPrioritas.Priotitas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface API {
    @Headers("Accept:application/json")
    @POST("getKegiatan")
    @FormUrlEncoded
    Call<List<Kegiatan>> getKegiatan(
            @Field("key") int key
    );

    @Headers("Accept:application/json")
    @POST("getLevelPrioritas")
    Call<List<Priotitas>> getPrioritas();

    @Headers("Accept:application/json")
    @POST("getLevelKesulitan")
    Call<List<Kesulitan>> getKesulitan();
}
