package com.gustu.logbook.fragment.home.interfaces

import android.view.View
import com.gustu.logbook.fragment.home.model.GetLogBook

interface HomeView {
    fun _onDataLoad(dataItemList: List<GetLogBook>?): View
    fun _onDataFail(): View
}
