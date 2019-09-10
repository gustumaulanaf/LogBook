package com.gustu.logbook.fragment.home.presenter

import android.util.Log

import com.gustu.logbook.fragment.home.interfaces.HomeView
import com.gustu.logbook.fragment.home.model.GetLogBook
import com.gustu.logbook.main.model.deleteLogbook.ResponseDeleteLogbook
import com.gustu.logbook.network.BaseURLJSON
import com.gustu.logbook.network.BaseURLXML
import com.gustu.logbook.sharePreferences.SharedPrefUtil

import java.util.ArrayList

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter(internal var homeView: HomeView) {
    internal var baseURLJSON: BaseURLJSON? = null
    internal var baseURLXML:BaseURLXML?=null
    internal val TAG = "HOME PRESENTER"
    internal var dataItemList: List<GetLogBook>? = ArrayList()

    init {
        if (this.baseURLJSON == null && this.baseURLXML==null) {
            this.baseURLJSON = BaseURLJSON()
            this.baseURLXML = BaseURLXML()
        }

    }

    fun getLogbook() {
        val key = SharedPrefUtil.getString("kode_pegawai")
        baseURLJSON!!.api.getLogbook(key!!).enqueue(object : Callback<List<GetLogBook>> {
            override fun onResponse(call: Call<List<GetLogBook>>, response: Response<List<GetLogBook>>) {
                if (response.isSuccessful) {
                    dataItemList = response.body()
                    homeView._onDataLoad(dataItemList)
                }
            }

            override fun onFailure(call: Call<List<GetLogBook>>, t: Throwable) {
                homeView._onDataFail()
            }
        })
    }
    fun deleteLogbook(idLogbook:String){
        baseURLXML!!.api.deleteLogbook(idLogbook).enqueue(object :Callback<ResponseDeleteLogbook>{
            override fun onResponse(call: Call<ResponseDeleteLogbook>, response: Response<ResponseDeleteLogbook>) {
                if (response.isSuccessful){
                    homeView._DataDeleted()
                }
            }

            override fun onFailure(call: Call<ResponseDeleteLogbook>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}
