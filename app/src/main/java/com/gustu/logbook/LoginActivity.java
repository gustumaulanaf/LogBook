package com.gustu.logbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText ETusername,ETpassword;
    Button BTmasuk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ETusername = findViewById(R.id.etUsername);
        ETpassword = findViewById(R.id.etPassword);
        BTmasuk = findViewById(R.id.btMasuk);
        BTmasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ETusername.getText().toString().isEmpty() || ETpassword.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this,"Form Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
    }
}
