package com.gustu.logbook.main.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gustu.logbook.R;
import com.gustu.logbook.main.adapter.kegiatan.KegiatanAdapter;
import com.gustu.logbook.main.interfaces.MainView;
import com.gustu.logbook.main.model.kegiatan.Kegiatan;
import com.gustu.logbook.main.model.levelKesulitan.Kesulitan;
import com.gustu.logbook.main.model.levelPrioritas.Priotitas;
import com.gustu.logbook.main.presenter.MainPresenter;
import com.gustu.logbook.sharePreferences.SharedPrefUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {
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
    @BindView(R.id.etPilihKegiatan)
    EditText pilihKegiatan;

    AppCompatDialog appCompatDialog;
    FloatingActionButton floatingActionButton;
    //    String[] arraytingkatKesulitan = new String[]{"Mudah", "Sedang", "Sulit"};
//    String [] arrayLevelPrioritas = new String[]{"1","2","3","4","5"};
    String kesulitan, prioritas;
    List<Kegiatan> kegiatanListMain = new ArrayList<>();
    List<Kesulitan> kesulitanList = new ArrayList<>();
    List<Priotitas> priotitasList = new ArrayList<>();
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton = findViewById(R.id.fabAdd);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddDialog();
            }
        });
        //Init Presenter
        initPresenter();
        //Init App CompatDialog
        appCompatDialog = new AppCompatDialog(this);
        appCompatDialog.setTitle("Tambah Log");
        appCompatDialog.setContentView(R.layout.item_tambah);
        ButterKnife.bind(this, appCompatDialog);
        //InitSpinner

    }

    private void initPresenter() {
        mainPresenter = new MainPresenter(this);
        mainPresenter.getKegiatan(1907);
        mainPresenter.getPrioritas();
        mainPresenter.getKesulitan();

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
                if (tanggalMulai.getText().toString().isEmpty() || tanggalSelesai.getText().toString().isEmpty() || keterangan.getText().toString().isEmpty() || kuantitas.getText().toString().isEmpty() || hasil.getText().toString().isEmpty() || kesulitan.isEmpty() || prioritas.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Form Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
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
        pilihKegiatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showKegiatanDialog(kegiatanListMain);
            }
        });
        appCompatDialog.show();
    }

    @Override
    public void _onKesulitanLoad(List<Kesulitan> kesulitanList) {
        List<String> arrayKesulitan = new ArrayList<String>();
        for (int i = 0; i < kesulitanList.size(); i++) {
            arrayKesulitan.add(kesulitanList.get(i).getRLKNAMA());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayKesulitan);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTingkatKesulitanJava.setAdapter(adapter);
    }

    @Override
    public void _onPrioritasLoad(List<Priotitas> priotitasList) {
        List<String> arrayPrioritas = new ArrayList<String>();
        for (int i = 0; i < priotitasList.size(); i++) {
            arrayPrioritas.add(priotitasList.get(i).getRLPNAMA());
        }
        ArrayAdapter<String> adapterLevelPrioritas = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayPrioritas);
        adapterLevelPrioritas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLevelPrioritasJava.setAdapter(adapterLevelPrioritas);
    }

    @Override
    public void _onKegiatanLoad(List<Kegiatan> kegiatanList) {
        kegiatanListMain.addAll(kegiatanList);
    }

    void showKegiatanDialog(List<Kegiatan> kegiatanList) {
        KegiatanAdapter kegiatanAdapter;
        kegiatanAdapter = new KegiatanAdapter(kegiatanList);
        AppCompatDialog appCompatDialog = new AppCompatDialog(this);
        appCompatDialog.setContentView(R.layout.kegiatan_dialog);
        RecyclerView recyclerView;
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = appCompatDialog.findViewById(R.id.buttonBawahKegiatan);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.batal:
                        appCompatDialog.dismiss();
                        break;
                    case R.id.pilih:
                        pilihKegiatan.setText(SharedPrefUtil.getString("nama_kegiatan"));
                        keterangan.setText(SharedPrefUtil.getString("keterangan_kegiatan"));
                        appCompatDialog.dismiss();
                        break;

                }
                return false;
            }
        });
        recyclerView = appCompatDialog.findViewById(R.id.rvKegiatan);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(kegiatanAdapter);
        appCompatDialog.show();
    }

    @Override
    public void _onFailed(String t) {

        Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show();
    }
}
