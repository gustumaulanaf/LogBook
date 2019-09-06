package com.gustu.logbook.fragment.home.view


import android.os.Bundle

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.gustu.logbook.R
import com.gustu.logbook.fragment.home.adapter.HomeAdapter
import com.gustu.logbook.fragment.home.interfaces.HomeView
import com.gustu.logbook.fragment.home.model.GetLogBook
import com.gustu.logbook.fragment.home.presenter.HomePresenter

import java.util.ArrayList
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(), HomeView {
    internal lateinit var view: View
    internal lateinit var homePresenter: HomePresenter
    internal lateinit var homeAdapter: HomeAdapter
    internal var dataItemListMain: MutableList<GetLogBook> = ArrayList()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.fragment_home, container, false)
        homePresenter = HomePresenter(this)
        homePresenter.getLogbook()
        // Inflate the layout for this fragment
        return view
    }

    override fun _onDataLoad(dataItemList: List<GetLogBook>?): View {
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
        Log.d("Home Fragment", "_onDataLoad: " + "Gagal Diload")
        return view
    }

    override fun onResume() {
        super.onResume()
        homeAdapter = HomeAdapter(dataItemListMain)
        homeAdapter.notifyDataSetChanged()
    }
}// Required empty public constructor
