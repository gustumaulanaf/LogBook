package com.gustu.logbook.login.presenter

import android.util.Log

import com.gustu.logbook.login.interfaces.LoginView
import com.gustu.logbook.login.model.LoginResponse
import com.gustu.logbook.network.loginnetwork.LoginBaseUrl

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(internal var loginView: LoginView) {
    internal var loginBaseUrl: LoginBaseUrl? = null

    init {
        if (this.loginBaseUrl == null) {
            this.loginBaseUrl = LoginBaseUrl()
        }
    }

    fun gotoLogin(username: String, password: String) {
        Log.d("LoginPresenter", "gotoLogin: $username$password")
        loginBaseUrl!!.loginApi.login(username, password).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    loginView._onLogin(loginResponse!!)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loginView._onFailed()
            }
        })
    }
}
