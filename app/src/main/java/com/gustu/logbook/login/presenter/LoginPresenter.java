package com.gustu.logbook.login.presenter;

import com.gustu.logbook.login.interfaces.LoginView;
import com.gustu.logbook.login.model.LoginResponse;
import com.gustu.logbook.network.loginnetwork.LoginBaseUrl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    LoginView loginView;
    LoginBaseUrl loginBaseUrl;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        if (this.loginBaseUrl==null){
            this.loginBaseUrl= new LoginBaseUrl();
        }
    }
    public void gotoLogin(String username , String password){
        loginBaseUrl.getLoginApi().login(username, password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    LoginResponse loginResponse = response.body();
                    loginView._onLogin(loginResponse);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                    loginView._onFailed();
            }
        });
    }
}
