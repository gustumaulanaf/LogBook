package com.gustu.logbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText tanggalMulai, tanggalSelesai, keterangan, kuantitas, hasil;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    Button btTambah;
    AppCompatDialog appCompatDialog;
    FloatingActionButton floatingActionButton;

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
        btTambah = appCompatDialog.findViewById(R.id.btTambahLog);
        tanggalMulai =appCompatDialog.findViewById(R.id.etTanggalMulai);
        tanggalSelesai = appCompatDialog.findViewById(R.id.etTanggalSelesai);
        keterangan = appCompatDialog.findViewById(R.id.etKeteranganKegiatan);
        kuantitas = appCompatDialog.findViewById(R.id.etKuantitas);
        hasil = appCompatDialog.findViewById(R.id.etHasil);
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
