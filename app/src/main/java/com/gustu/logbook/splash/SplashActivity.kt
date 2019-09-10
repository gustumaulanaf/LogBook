package com.gustu.logbook.splash

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog

import com.gustu.logbook.R
import com.gustu.logbook.login.interfaces.LoginView
import com.gustu.logbook.login.model.LoginResponse
import com.gustu.logbook.login.presenter.LoginPresenter
import com.gustu.logbook.login.view.LoginActivity
import com.gustu.logbook.main.view.MainActivity
import com.gustu.logbook.sharePreferences.SharedPrefUtil

class SplashActivity : AppCompatActivity(),LoginView {
    lateinit var loginPresenter : LoginPresenter
    internal var waktuLoading = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        loginPresenter = LoginPresenter(this)
        Handler().postDelayed({
            if (SharedPrefUtil.getBoolean("isLogin")){
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
            else{
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }

        }, waktuLoading.toLong())
    }
    override fun _onLogin(loginResponse: LoginResponse) {
        startActivity(Intent(this@SplashActivity,MainActivity::class.java))
        finish()
    }

    override fun _onFailed() {
        var alertDialog = AlertDialog.Builder(this)
        alertDialog.setMessage("Tidak Ada Koneksi Internet")
        alertDialog.setTitle("Peringatan")
        alertDialog.setPositiveButton("Muat Ulang",
                DialogInterface.OnClickListener { dialogInterface, i ->
                    startActivity(Intent(this@SplashActivity,SplashActivity::class.java))
                    finish()
                })
        alertDialog.setNegativeButton("Tutup Aplikasi",
                DialogInterface.OnClickListener { dialogInterface, i ->
                    finish()
                })
    }
}
