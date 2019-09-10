package com.gustu.logbook.main.presenter

import android.util.Log

import com.gustu.logbook.main.interfaces.MainView
import com.gustu.logbook.main.model.addLogbook.ResponseSaveLogbook
import com.gustu.logbook.main.model.kegiatan.Kegiatan
import com.gustu.logbook.main.model.levelKesulitan.Kesulitan
import com.gustu.logbook.main.model.levelPrioritas.Priotitas
import com.gustu.logbook.network.BaseURLJSON
import com.gustu.logbook.network.BaseURLXML
import com.gustu.logbook.sharePreferences.SharedPrefUtil

import java.util.ArrayList

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(internal var mainView: MainView) {
    internal var baseURLJSON: BaseURLJSON? = null
    internal var kegiatanList: List<Kegiatan>? = ArrayList()
    internal var kesulitanList: List<Kesulitan>? = ArrayList()
    internal var priotitasList: List<Priotitas>? = ArrayList()
    internal var baseURLXML: BaseURLXML? = null

    init {
        if (this.baseURLJSON == null) {
            this.baseURLJSON = BaseURLJSON()
        }
        if (this.baseURLXML == null) {
            this.baseURLXML = BaseURLXML()
        }
    }

    fun getKegiatan(key: Int) {
        baseURLJSON!!.api.getKegiatan(key).enqueue(object : Callback<List<Kegiatan>> {
            override fun onResponse(call: Call<List<Kegiatan>>, response: Response<List<Kegiatan>>) {
                if (response.isSuccessful) {
                    kegiatanList = response.body()
                    mainView._onKegiatanLoad(kegiatanList!!)
                }
            }

            override fun onFailure(call: Call<List<Kegiatan>>, t: Throwable) {
                mainView._onFailed(t.toString())
            }
        })
    }

    fun getPrioritas() {
        baseURLJSON!!.api.prioritas.enqueue(object : Callback<List<Priotitas>> {
            override fun onResponse(call: Call<List<Priotitas>>, response: Response<List<Priotitas>>) {
                if (response.isSuccessful) {
                    priotitasList = response.body()
                    mainView._onPrioritasLoad(priotitasList!!)
                }
            }

            override fun onFailure(call: Call<List<Priotitas>>, t: Throwable) {
                mainView._onFailed(t.toString())
            }
        })
    }

    fun getKesulitan() {
        baseURLJSON!!.api.kesulitan.enqueue(object : Callback<List<Kesulitan>> {
            override fun onResponse(call: Call<List<Kesulitan>>, response: Response<List<Kesulitan>>) {
                if (response.isSuccessful) {
                    kesulitanList = response.body()
                    mainView._onKesulitanLoad(kesulitanList!!)
                }
            }

            override fun onFailure(call: Call<List<Kesulitan>>, t: Throwable) {
                mainView._onFailed(t.toString())
            }
        })
    }

    fun saveLogbook(tanggalMulai: String, tanggalAkhir: String, kodeKegiatan: String, namaKegiatan: String, keteranganKegiatan: String, outputLogbook: String, tingkatKesulitan: String, levelPrioritas: String, jumlahKegiatan: String) {
        val kode_pegawai = SharedPrefUtil.getString("kode_pegawai")
        val namaPegawai = SharedPrefUtil.getString("nama")
        val kodeunit = SharedPrefUtil.getString("kode_unit")
        baseURLXML!!.api.saveLogbook(tanggalMulai, tanggalAkhir, kode_pegawai!!, namaPegawai!!, kodeunit!!, kodeKegiatan, namaKegiatan, keteranganKegiatan, outputLogbook, tingkatKesulitan, levelPrioritas, jumlahKegiatan).enqueue(object : Callback<ResponseSaveLogbook> {
            override fun onResponse(call: Call<ResponseSaveLogbook>, response: Response<ResponseSaveLogbook>) {
                if (response.isSuccessful) {
                    mainView._onDataAdd()
                }
            }

            override fun onFailure(call: Call<ResponseSaveLogbook>, t: Throwable) {
                mainView._onDataFailedAdd()
            }
        })
    }
    fun deleteLogBook(id:String){
        baseURLJSON!!.api.deleteLogbook(id)
    }
}
