package com.gustu.logbook.fragment.home.view


import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.gustu.logbook.R
import com.gustu.logbook.fragment.home.adapter.HomeAdapter
import com.gustu.logbook.fragment.home.interfaces.HomeView
import com.gustu.logbook.fragment.home.model.GetLogBook
import com.gustu.logbook.fragment.home.presenter.HomePresenter
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.fragment_home.*

import java.util.ArrayList
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_home.view.swipeHome

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(), HomeView {


    internal lateinit var view: View
    internal lateinit var homePresenter: HomePresenter
    internal lateinit var homeAdapter: HomeAdapter
    internal var dataItemListMain: MutableList<GetLogBook> = ArrayList()
    internal lateinit var progressDialog:SpotsDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.fragment_home, container, false)
        progressDialog = SpotsDialog(activity,R.style.DialogCustom)
        progressDialog.show()
        homePresenter = HomePresenter(this)
        homePresenter.getLogbook()
        view.swipeHome.setOnRefreshListener {
            progressDialog.show()
            homePresenter.getLogbook()
            swipeHome.isRefreshing = false
        }

        // Inflate the layout for this fragment
        return view
    }

    override fun _onDataLoad(dataItemList: List<GetLogBook>?): View {
        progressDialog.dismiss()
        Log.d("Home Fragment", "_onDataLoad: " + "Berhasil Diload")
        homeAdapter = HomeAdapter(dataItemList)
        dataItemListMain.addAll(dataItemList!!)
        homeAdapter.setContext(context!!)
        view.rvHistoryLogbook.setHasFixedSize(true)
        view.rvHistoryLogbook.layoutManager = LinearLayoutManager(activity)
        view.rvHistoryLogbook.adapter = homeAdapter
        return view
    }

    override fun _onDataFail(): View {
        progressDialog.dismiss()
        Log.d("Home Fragment", "_onDataLoad: " + "Gagal Diload")
        return view
    }

    override fun onResume() {
        super.onResume()
        homeAdapter = HomeAdapter(dataItemListMain)
        homeAdapter.notifyDataSetChanged()
    }

    override fun _DataDeleted(): View {
        progressDialog.dismiss()
        homeAdapter.notifyDataSetChanged()
        return view
    }

    override fun _DataFailDelete(): View {
    progressDialog.dismiss()
        Toast.makeText(activity,"Gagal Dihapus",Toast.LENGTH_SHORT).show()
        return  view
    }
}// Required empty public constructor
