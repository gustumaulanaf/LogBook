package com.gustu.logbook.main.interfaces;

import com.gustu.logbook.main.model.kegiatan.Kegiatan;
import com.gustu.logbook.main.model.levelKesulitan.Kesulitan;
import com.gustu.logbook.main.model.levelPrioritas.Priotitas;

import java.util.List;

public interface MainView {
    void _onKesulitanLoad(List<Kesulitan> kesulitanList);
    void _onPrioritasLoad(List<Priotitas> priotitasList);
    void _onKegiatanLoad(List<Kegiatan> kegiatanList);
    void _onFailed(String t);
}
