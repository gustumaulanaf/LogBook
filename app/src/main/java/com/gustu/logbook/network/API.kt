package com.gustu.logbook.network

import com.gustu.logbook.fragment.home.model.GetLogBook
import com.gustu.logbook.main.model.addLogbook.ResponseSaveLogbook
import com.gustu.logbook.main.model.kegiatan.Kegiatan
import com.gustu.logbook.main.model.levelKesulitan.Kesulitan
import com.gustu.logbook.main.model.levelPrioritas.Priotitas

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface API {

    //Level Prioritas
    @get:Headers("Accept:application/json")
    @get:GET("getLevelPrioritas")
    val prioritas: Call<List<Priotitas>>

    //Level Kesulitan
    @get:Headers("Accept:application/json")
    @get:GET("getLevelKesulitan")
    val kesulitan: Call<List<Kesulitan>>

    //Kegiatan
    @Headers("Accept:application/json")
    @POST("getKegiatan")
    @FormUrlEncoded
    fun getKegiatan(
            @Field("key") key: Int
    ): Call<List<Kegiatan>>

    //saveLogbook
    @Headers("Accept: application/xml")
    @POST("saveLogbook")
    @FormUrlEncoded
    fun saveLogbook(
            @Field("id_logbook") id: String,
            @Field("tanggal_awal") tangggalAwal: String,
            @Field("tanggal_ahir") tanggalAkhir: String,
            @Field("kode_pegawai") kodePegawai: String,
            @Field("nama_pegawai") namaPegawai: String,
            @Field("kode_unit") kodeUnit: String,
            @Field("kode_kegiatan") kodeKegiatan: String,
            @Field("nama_kegiatan") namaKegiatan: String,
            @Field("keterangan_logbook") keteranganLogBook: String,
            @Field("output_logbook") outputLogbook: String,
            @Field("level_kesulitan") levelKesulitan: String,
            @Field("level_prioritas") levelPrioritas: String,
            @Field("jumlah_kegiatan") jumlahKegiatan: String,
            @Field("jenis_source_data") jenisSourceData: String
    ): Call<ResponseSaveLogbook>

    //GET LOGBOOK
    @Headers("Accept:application/json")
    @POST("getLogBook")
    @FormUrlEncoded
    fun getLogbook(
            @Field("key") key: String
    ): Call<List<GetLogBook>>

    //DELETE
    @POST("deleteLogbook")
    @FormUrlEncoded
    fun deleteLogbook(
            @Field("id_logbook") id_logbook : String
    )
   // EDIT
    @Headers("Accept:application/json")
    @POST("updateLogbook")
    @FormUrlEncoded
    fun UpdateLogbook(
           @Field("id_logbook") id: String,
           @Field("tanggal_awal") tangggalAwal: String,
           @Field("tanggal_ahir") tanggalAkhir: String,
           @Field("kode_pegawai") kodePegawai: String,
           @Field("nama_pegawai") namaPegawai: String,
           @Field("kode_unit") kodeUnit: String,
           @Field("kode_kegiatan") kodeKegiatan: String,
           @Field("nama_kegiatan") namaKegiatan: String,
           @Field("keterangan_logbook") keteranganLogBook: String,
           @Field("output_logbook") outputLogbook: String,
           @Field("level_kesulitan") levelKesulitan: String,
           @Field("level_prioritas") levelPrioritas: String,
           @Field("jumlah_kegiatan") jumlahKegiatan: String,
           @Field("jenis_source_data") jenisSourceData: String
   ) : Call<ResponseSaveLogbook>
}
