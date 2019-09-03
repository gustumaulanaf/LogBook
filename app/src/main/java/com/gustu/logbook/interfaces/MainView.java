package com.gustu.logbook.interfaces;

import com.gustu.logbook.models.kegiatan.Kegiatan;
import com.gustu.logbook.models.levelKesulitan.Kesulitan;
import com.gustu.logbook.models.levelPrioritas.Priotitas;

import java.util.List;

public interface MainView {
    void _onKesulitanLoad(List<Kesulitan> kesulitanList);
    void _onPrioritasLoad(List<Priotitas> priotitasList);
    void _onKegiatanLoad(List<Kegiatan> kegiatanList);
    void _onFailed(String t);
}
