package com.gustu.logbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.spTingkatKesulitan)
    Spinner spTingkatKesulitanJava;
    @BindView(R.id.spLevelPrioritas)
    Spinner spLevelPrioritasJava;
    @BindView(R.id.etTanggalMulai)
    EditText tanggalMulai;
    @BindView(R.id.etTanggalSelesai)
    EditText tanggalSelesai;
    @BindView(R.id.etKeteranganKegiatan)
    EditText keterangan;
    @BindView(R.id.etKuantitas)
    EditText kuantitas;
    @BindView(R.id.etHasil)
    EditText hasil;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    @BindView(R.id.btTambahLog)
    Button btTambah;
    AppCompatDialog appCompatDialog;
    FloatingActionButton floatingActionButton;
    String[] arraytingkatKesulitan = new String[]{"Mudah", "Sedang", "Sulit"};
    String [] arrayLevelPrioritas = new String[]{"1","2","3","4","5"};
    String kesulitan,prioritas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//    btTambah.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            showDateDialog(tanggalMulai);
//        }
//    });
        floatingActionButton = findViewById(R.id.fabAdd);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddDialog();
            }
        });


    }

    private void showDateDialog(final EditText editText) {
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                editText.setText(dateFormatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void showAddDialog() {

        appCompatDialog = new AppCompatDialog(this);
        appCompatDialog.setTitle("Tambah Log");
        appCompatDialog.setContentView(R.layout.item_tambah);
        ButterKnife.bind(this, appCompatDialog);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arraytingkatKesulitan);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTingkatKesulitanJava.setAdapter(adapter);
        ArrayAdapter<String> adapterLevelPrioritas = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayLevelPrioritas);
        adapterLevelPrioritas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLevelPrioritasJava.setAdapter(adapterLevelPrioritas);
        spLevelPrioritasJava.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                prioritas = spLevelPrioritasJava.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spTingkatKesulitanJava.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 kesulitan = spTingkatKesulitanJava.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tanggalMulai.getText().toString().isEmpty()||tanggalSelesai.getText().toString().isEmpty()||keterangan.getText().toString().isEmpty()||kuantitas.getText().toString().isEmpty()||hasil.getText().toString().isEmpty()||kesulitan.isEmpty()||prioritas.isEmpty()){
                    Toast.makeText(MainActivity.this,"Form Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Berhasil Ditambahkan",Toast.LENGTH_SHORT).show();
                }
            }
        });
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        tanggalMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(tanggalMulai);
            }
        });
        tanggalSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(tanggalSelesai);
            }
        });
        appCompatDialog.show();
    }
}
