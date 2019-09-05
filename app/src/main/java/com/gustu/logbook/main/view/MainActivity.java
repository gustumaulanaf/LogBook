package com.gustu.logbook.main.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
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
import com.gustu.logbook.fragment.home.view.HomeFragment;
import com.gustu.logbook.fragment.profile.ProfileFragment;
import com.gustu.logbook.main.adapter.kegiatan.KegiatanAdapter;
import com.gustu.logbook.main.interfaces.MainView;
import com.gustu.logbook.main.model.kegiatan.Kegiatan;
import com.gustu.logbook.main.model.levelKesulitan.Kesulitan;
import com.gustu.logbook.main.model.levelPrioritas.Priotitas;
import com.gustu.logbook.main.presenter.MainPresenter;
import com.gustu.logbook.sharePreferences.SharedPrefUtil;

import java.text.DateFormat;
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
    @BindView(R.id.etOutput)
    EditText outputLogbook;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    @BindView(R.id.btTambahLog)
    Button btTambah;
    @BindView(R.id.etPilihKegiatan)
    EditText pilihKegiatan;
    @BindView(R.id.etJumlahKegiatan)
    EditText jumlahKegiatan;

    BottomNavigationView menuBawah;
    AppCompatDialog appCompatDialog;
    FloatingActionButton floatingActionButton;
    String tanggaljam;
    String kesulitan, prioritas;
    List<Kegiatan> kegiatanListMain = new ArrayList<>();
    List<Kesulitan> kesulitanListMain = new ArrayList<>();
    List<Priotitas> priotitasListMain = new ArrayList<>();
    String kodeKesulitan, kodePrioritas, kodeKegiatan;
    MainPresenter mainPresenter;
    Runnable runnable;
    Handler handler = new Handler();
    int delay = 1000;

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
        gotoFragment(new HomeFragment());
        menuBawah = findViewById(R.id.btMenuMain);
        menuBawah.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.homeMenu:
                        gotoFragment(new HomeFragment());
                        floatingActionButton.show();
                        break;
                    case R.id.profileMenu:
                        gotoFragment(new ProfileFragment());
                        floatingActionButton.hide();
                        break;
                }
                return false;
            }
        });
        //Init Presenter
        initPresenter();
        //Init App CompatDialog
        appCompatDialog = new AppCompatDialog(this);
        appCompatDialog.setTitle("Tambah Log");
        appCompatDialog.setContentView(R.layout.item_tambah);
        ButterKnife.bind(this, appCompatDialog);
        getTanggalJam();
    }

    private boolean gotoFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.FrameMain, fragment).commit();
        return true;
    }

    private void initPresenter() {
        mainPresenter = new MainPresenter(this);
        mainPresenter.getKegiatan(1907);
        mainPresenter.getPrioritas();
        mainPresenter.getKesulitan();

    }

    private void getTanggalJam() {
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                Locale locale = new Locale("in", "ID");
                DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss", locale);
                tanggaljam = df.format(Calendar.getInstance().getTime());
                Log.d("MainActivity", "run: " + tanggaljam);
                handler.postDelayed(runnable, delay);
            }
        }, delay);
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
                kodePrioritas = priotitasListMain.get(i).getRLPKODE();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spTingkatKesulitanJava.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                kesulitan = spTingkatKesulitanJava.getItemAtPosition(i).toString();
                kodeKesulitan = kesulitanListMain.get(i).getRLKKODE();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tanggalMulai.getText().toString().isEmpty() || tanggalSelesai.getText().toString().isEmpty() || keterangan.getText().toString().isEmpty() || outputLogbook.getText().toString().isEmpty() || kesulitan.isEmpty() || prioritas.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Form Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                } else {
                    mainPresenter.saveLogbook(tanggaljam, tanggalMulai.getText().toString(), tanggalSelesai.getText().toString(), SharedPrefUtil.getString("kode_kegiatan"), pilihKegiatan.getText().toString(), keterangan.getText().toString(), outputLogbook.getText().toString(), kodeKesulitan, kodePrioritas, jumlahKegiatan.getText().toString());
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
        kesulitanListMain.addAll(kesulitanList);
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
        priotitasListMain.addAll(priotitasList);
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

    @Override
    public void _onDataAdd() {
        appCompatDialog.dismiss();
        gotoFragment(new HomeFragment());
    }

    @Override
    public void _onDataFailedAdd() {

        Toast.makeText(this, "Log Gagal Ditambahkan", Toast.LENGTH_SHORT).show();
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
