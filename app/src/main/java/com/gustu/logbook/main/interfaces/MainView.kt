package com.gustu.logbook.main.interfaces

import com.gustu.logbook.main.model.kegiatan.Kegiatan
import com.gustu.logbook.main.model.levelKesulitan.Kesulitan
import com.gustu.logbook.main.model.levelPrioritas.Priotitas

interface MainView {
    fun _onKesulitanLoad(kesulitanList: List<Kesulitan>)
    fun _onPrioritasLoad(priotitasList: List<Priotitas>)
    fun _onKegiatanLoad(kegiatanList: List<Kegiatan>)
    fun _onDataAdd()
    fun _onDataFailedAdd()
    fun _onFailed(t: String)
}
