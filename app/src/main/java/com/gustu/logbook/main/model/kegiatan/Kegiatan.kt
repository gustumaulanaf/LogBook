package com.gustu.logbook.main.model.kegiatan

import com.google.gson.annotations.SerializedName

class Kegiatan {

    @SerializedName("MKL_KETERANGAN")
    var mklketerangan: String? = null

    @SerializedName("MKL_NAMA")
    var mklnama: String? = null

    @SerializedName("MKL_KODE")
    var mklkode: String? = null

    override fun toString(): String {
        return "Kegiatan{" +
                "mKL_KETERANGAN = '" + mklketerangan + '\''.toString() +
                ",mKL_NAMA = '" + mklnama + '\''.toString() +
                ",mKL_KODE = '" + mklkode + '\''.toString() +
                "}"
    }
}