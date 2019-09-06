package com.gustu.logbook.login.view

import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast

import com.gustu.logbook.R
import com.gustu.logbook.login.interfaces.LoginView
import com.gustu.logbook.login.model.LoginResponse
import com.gustu.logbook.login.presenter.LoginPresenter
import com.gustu.logbook.main.view.MainActivity
import com.gustu.logbook.sharePreferences.SharedPrefUtil
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {
    internal lateinit var loginPresenter: LoginPresenter
    internal lateinit var progressDialog: SpotsDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initPresenter()
        progressDialog = SpotsDialog(this, R.style.DialogCustom)
        btMasuk.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this@LoginActivity, "Form Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
            } else {
                progressDialog.show()
                loginPresenter.gotoLogin(username, password)
            }
        }

    }

    private fun initPresenter() {
        loginPresenter = LoginPresenter(this)
    }


    override fun _onLogin(loginResponse: LoginResponse) {
        progressDialog.dismiss()
        SharedPrefUtil.saveBoolean("isLogin", true)
        SharedPrefUtil.saveString("nama", loginResponse.jsonMember0!!.mpgnamagelar!!)
        SharedPrefUtil.saveString("password",loginResponse.jsonMember0!!.mpghandkey!!)
        SharedPrefUtil.saveString("kode_pegawai", loginResponse.jsonMember0!!.mpgkode!!)
        SharedPrefUtil.saveString("kode_unit", loginResponse.jsonMember0!!.msukode!!)
        SharedPrefUtil.saveString("nip", loginResponse.jsonMember0!!.mpgnip!!)
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }

    override fun _onFailed() {
        progressDialog.dismiss()
        Toast.makeText(this, "Login Gagal", Toast.LENGTH_SHORT).show()
    }
}
