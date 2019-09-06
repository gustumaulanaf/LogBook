package com.gustu.logbook.network.loginnetwork

import com.gustu.logbook.login.model.LoginResponse

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginAPI {
    //Untuk Login
    @Headers("Accept:application/json")
    @FormUrlEncoded
    @POST("login")
    fun login(
            @Field("inputUsername") username: String,
            @Field("inputPassword") password: String
    ): Call<LoginResponse>
}
