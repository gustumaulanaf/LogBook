package com.gustu.logbook.login.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.gustu.logbook.R;
import com.gustu.logbook.login.interfaces.LoginView;
import com.gustu.logbook.login.model.LoginResponse;
import com.gustu.logbook.login.presenter.LoginPresenter;
import com.gustu.logbook.main.view.MainActivity;
import com.gustu.logbook.sharePreferences.SharedPrefUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;

public class LoginActivity extends AppCompatActivity implements LoginView {
    @BindView(R.id.etUsername)
    EditText ETusername;
    @BindView(R.id.etPassword)
    EditText ETpassword;
    LoginPresenter loginPresenter;
    AlertDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initPresenter();
        progressDialog = new SpotsDialog(this,R.style.DialogCustom);

    }

    private void initPresenter() {
        loginPresenter = new LoginPresenter(this);
    }

    @OnClick(R.id.btMasuk)
    public void login(){
        String username = ETusername.getText().toString();
        String password = ETpassword.getText().toString();
        if (username.isEmpty() || password.isEmpty()){
            Toast.makeText(LoginActivity.this,"Form Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
        }
        else {
            progressDialog.show();
            loginPresenter.gotoLogin(username,password);
        }
    }
    @Override
    public void _onLogin(LoginResponse loginResponse) {
        progressDialog.dismiss();
        SharedPrefUtil.saveBoolean("isLogin",true);
        SharedPrefUtil.saveString("nama",loginResponse.getJsonMember0().getMPGNAMAGELAR());
        SharedPrefUtil.saveString("kode_pegawai",loginResponse.getJsonMember0().getMPGKODE());
        SharedPrefUtil.saveString("kode_unit",loginResponse.getJsonMember0().getMSUKODE());
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void _onFailed() {
        progressDialog.dismiss();
        Toast.makeText(this,"Login Gagal",Toast.LENGTH_SHORT).show();
    }
}
