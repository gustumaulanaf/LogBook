package com.gustu.logbook.main.view

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.gustu.logbook.R
import com.gustu.logbook.fragment.home.view.HomeFragment
import com.gustu.logbook.fragment.profile.ProfileFragment
import com.gustu.logbook.main.adapter.kegiatan.KegiatanAdapter
import com.gustu.logbook.main.interfaces.MainView
import com.gustu.logbook.main.model.kegiatan.Kegiatan
import com.gustu.logbook.main.model.levelKesulitan.Kesulitan
import com.gustu.logbook.main.model.levelPrioritas.Priotitas
import com.gustu.logbook.main.presenter.MainPresenter
import com.gustu.logbook.sharePreferences.SharedPrefUtil

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.Locale
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_tambah.*
import kotlinx.android.synthetic.main.kegiatan_dialog.*

class MainActivity : AppCompatActivity(), MainView {

    internal lateinit var datePickerDialog: DatePickerDialog
    internal lateinit var dateFormatter: SimpleDateFormat
    internal lateinit var appCompatDialog: AppCompatDialog
    internal lateinit var kesulitan: String
    internal lateinit var prioritas: String
    internal var kegiatanListMain: MutableList<Kegiatan> = ArrayList()
    internal var kesulitanListMain: MutableList<Kesulitan> = ArrayList()
    internal var priotitasListMain: MutableList<Priotitas> = ArrayList()
    internal var kodeKesulitan: String? = null
    internal var kodePrioritas: String? = null
    internal var kodeKegiatan: String? = null
    internal lateinit var spKesulitan: Spinner
    internal lateinit var spPrioritas: Spinner
    internal lateinit var btTambah: Button
    internal lateinit var mainPresenter: MainPresenter
    internal lateinit var runnable: Runnable
    internal var handler = Handler()
    internal var delay = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fabAdd.setOnClickListener { showAddDialog() }
        gotoFragment(HomeFragment())

        btMenuMain.setOnNavigationItemSelectedListener { menuItem ->
            val fragment: Fragment? = null
            when (menuItem.itemId) {
                R.id.homeMenu -> {
                    gotoFragment(HomeFragment())
                    fabAdd.show()
                }
                R.id.profileMenu -> {
                    gotoFragment(ProfileFragment())
                    fabAdd.hide()
                }
            }
            false
        }
        //Init Presenter
        initPresenter()
        //Init App CompatDialog
        appCompatDialog = AppCompatDialog(this)
        appCompatDialog.setTitle("Tambah Log")
        appCompatDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        appCompatDialog.setContentView(R.layout.item_tambah)
        spPrioritas = appCompatDialog.spLevelPrioritas
        spKesulitan = appCompatDialog.spTingkatKesulitan
        btTambah = appCompatDialog.btTambahLog
    }

    private fun gotoFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.FrameMain, fragment).commit()
        return true
    }

    private fun initPresenter() {
        mainPresenter = MainPresenter(this)
        mainPresenter.getKegiatan(SharedPrefUtil.getString("kode_pegawai")!!.toInt())
        mainPresenter.getPrioritas()
        mainPresenter.getKesulitan()

    }

    private fun showDateDialog(editText: EditText?) {
        val newCalendar = Calendar.getInstance()
        datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            val newDate = Calendar.getInstance()
            newDate.set(year, monthOfYear, dayOfMonth)
            editText!!.setText(dateFormatter.format(newDate.time))
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH))
        datePickerDialog.show()
    }

    fun showAddDialog() {
        spPrioritas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                prioritas = spPrioritas.getItemAtPosition(i).toString()
                kodePrioritas = priotitasListMain[i].rlpkode
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {

            }
        }
        spKesulitan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                kesulitan = spKesulitan.getItemAtPosition(i).toString()
                kodeKesulitan = kesulitanListMain[i].rlkkode

            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {

            }
        }
        btTambah.setOnClickListener {
            if (appCompatDialog.etTanggalMulai.text.toString().isEmpty() || appCompatDialog.etTanggalSelesai.text.toString().isEmpty() || appCompatDialog.etKeteranganKegiatan.text.toString().isEmpty() || appCompatDialog.etOutput.text.toString().isEmpty() || kesulitan.isEmpty() || prioritas.isEmpty()) {
                Toast.makeText(this@MainActivity, "Form Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
            } else {
                mainPresenter.saveLogbook(appCompatDialog.etTanggalMulai.text.toString(), appCompatDialog.etTanggalSelesai.text.toString(), SharedPrefUtil.getString("kode_kegiatan")!!, appCompatDialog.etPilihKegiatan.text.toString(), appCompatDialog.etKeteranganKegiatan.text.toString(), appCompatDialog.etOutput.text.toString(), kodeKesulitan!!, kodePrioritas!!, appCompatDialog.etJumlahKegiatan.text.toString())
            }
        }
        dateFormatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US)
        appCompatDialog.etTanggalMulai.setOnClickListener { showDateDialog(appCompatDialog.etTanggalMulai) }
        appCompatDialog.etTanggalSelesai.setOnClickListener { showDateDialog(appCompatDialog.etTanggalSelesai) }
        appCompatDialog.etPilihKegiatan.setOnClickListener { showKegiatanDialog(kegiatanListMain) }
        appCompatDialog.show()
    }


    override fun _onKesulitanLoad(kesulitanList: List<Kesulitan>) {
        kesulitanListMain.addAll(kesulitanList)
        val arrayKesulitan = ArrayList<String>()
        for (i in kesulitanList.indices) {
            arrayKesulitan.add(kesulitanList[i].rlknama!!)
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayKesulitan)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spKesulitan.adapter = adapter
    }

    override fun _onPrioritasLoad(priotitasList: List<Priotitas>) {
        priotitasListMain.addAll(priotitasList)
        val arrayPrioritas = ArrayList<String>()
        for (i in priotitasList.indices) {
            arrayPrioritas.add(priotitasList[i].rlpnama!!)
        }
        val adapterLevelPrioritas = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayPrioritas)
        adapterLevelPrioritas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spPrioritas.adapter = adapterLevelPrioritas
    }

    override fun _onKegiatanLoad(kegiatanList: List<Kegiatan>) {
        kegiatanListMain.addAll(kegiatanList)
    }

    override fun _onDataAdd() {
        appCompatDialog.dismiss()
        gotoFragment(HomeFragment())
    }

    override fun _onDataFailedAdd() {

        Toast.makeText(this, "Log Gagal Ditambahkan", Toast.LENGTH_SHORT).show()
    }

    internal fun showKegiatanDialog(kegiatanList: List<Kegiatan>) {
        val kegiatanAdapter: KegiatanAdapter
        kegiatanAdapter = KegiatanAdapter(kegiatanList)
        val appCompatDialog2 = AppCompatDialog(this)
        appCompatDialog2.setContentView(R.layout.kegiatan_dialog)
        val recyclerView: RecyclerView?
        val bottomNavigationView: BottomNavigationView?
        bottomNavigationView = appCompatDialog2.findViewById(R.id.buttonBawahKegiatan)
        bottomNavigationView!!.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.batal -> appCompatDialog2.dismiss()
                R.id.pilih -> {
                    appCompatDialog.etPilihKegiatan.setText(SharedPrefUtil.getString("nama_kegiatan"))
                    appCompatDialog.etKeteranganKegiatan.setText(SharedPrefUtil.getString("keterangan_kegiatan"))
                    appCompatDialog2.dismiss()
                }
            }
            false
        }
        recyclerView = appCompatDialog2.rvKegiatan
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = kegiatanAdapter
        appCompatDialog2.show()
    }

    override fun _onFailed(t: String) {

        Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()
    }
}
