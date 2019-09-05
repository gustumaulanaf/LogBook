package com.gustu.logbook.fragment.home.interfaces;

import android.view.View;

import com.gustu.logbook.fragment.home.model.DataItem;
import com.gustu.logbook.fragment.home.model.LogbookResponse;

import java.util.List;

public interface HomeView {
    View _onDataLoad(List<DataItem> dataItemList);
    View _onDataFail();
}
