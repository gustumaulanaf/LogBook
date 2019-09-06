package com.gustu.logbook.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class BaseURLXML {
    internal var retrofit: Retrofit? = null
    val api: API
        get() {
            val BASE_URL = "http://10.1.1.116/instikp/webservices/index.php/logbook/Logbook_services/"
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .client(OkHttpClient())
                        .addConverterFactory(SimpleXmlConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build()

            }
            return retrofit!!.create(API::class.java)
        }
}
