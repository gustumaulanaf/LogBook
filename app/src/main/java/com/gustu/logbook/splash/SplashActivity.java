package com.gustu.logbook.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.gustu.logbook.R;
import com.gustu.logbook.login.view.LoginActivity;

public class SplashActivity extends AppCompatActivity {
int waktuLoading = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
          overridePendingTransition(R.anim.fade_out,R.anim.fade_in);
          finish();

        }
    },waktuLoading);
    }
}
