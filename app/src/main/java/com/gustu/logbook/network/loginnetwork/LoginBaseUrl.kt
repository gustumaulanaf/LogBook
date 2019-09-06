package com.gustu.logbook.network.loginnetwork

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginBaseUrl {
    internal var retrofit: Retrofit? = null

    val loginApi: LoginAPI
        get() {
            val BASE_URL = "http://10.1.1.116/instikp/webservices/index.php/services/login_services/"
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build()
            }
            return retrofit!!.create(LoginAPI::class.java!!)
        }
}
