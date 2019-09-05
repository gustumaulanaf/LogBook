package com.gustu.logbook.fragment.home.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gustu.logbook.R;
import com.gustu.logbook.fragment.home.adapter.HomeAdapter;
import com.gustu.logbook.fragment.home.interfaces.HomeView;
import com.gustu.logbook.fragment.home.model.DataItem;
import com.gustu.logbook.fragment.home.presenter.HomePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeView {

    @BindView(R.id.rvHistoryLogbook)
    RecyclerView recyclerView;
    View view;
    HomePresenter homePresenter;
    HomeAdapter homeAdapter;
    List<DataItem> dataItemListMain = new ArrayList<>();
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_home,container,false);
        ButterKnife.bind(this,view);
        homePresenter = new HomePresenter(this);
        homePresenter.getLogbook();
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public View _onDataLoad(List<DataItem> dataItemList) {
        Log.d("Home Fragment", "_onDataLoad: "+"Berhasil Diload");
        homeAdapter = new HomeAdapter(dataItemList);
        dataItemListMain.addAll(dataItemList);
        homeAdapter.setContext(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(homeAdapter);
        return view;
    }

    @Override
    public View _onDataFail() {
        Log.d("Home Fragment", "_onDataLoad: "+"Gagal Diload");
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        homeAdapter = new HomeAdapter(dataItemListMain);
        homeAdapter.notifyDataSetChanged();
    }
}
