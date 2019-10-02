package com.gustu.logbook.fragment.home.presenter

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.gustu.logbook.main.model.deleteLogbook.ResponseDeleteLogbook
import com.gustu.logbook.network.BaseURLJSON
import com.gustu.logbook.network.BaseURLXML
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeletePresenter(var context:Context) {
    var baseURLXML:BaseURLXML? = null
    init {
        if (this.baseURLXML==null){
            this.baseURLXML = BaseURLXML()
        }
    }
    fun deleteLogbook(idLogbook:String){
        baseURLXML!!.api.deleteLogbook(idLogbook).enqueue(object : Callback<ResponseDeleteLogbook> {
            override fun onResponse(call: Call<ResponseDeleteLogbook>, response: Response<ResponseDeleteLogbook>) {
                if (response.isSuccessful){

                }
            }

            override fun onFailure(call: Call<ResponseDeleteLogbook>, t: Throwable) {
                Log.d("DELETE",t.toString())
              }
        })
    }
}