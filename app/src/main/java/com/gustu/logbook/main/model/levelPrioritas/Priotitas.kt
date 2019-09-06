package com.gustu.logbook.main.model.levelPrioritas

import com.google.gson.annotations.SerializedName

class Priotitas {

    @SerializedName("RLP_KODE")
    var rlpkode: String? = null

    @SerializedName("RLP_ISAKTIF")
    var rlpisaktif: String? = null

    @SerializedName("RLP_USERUPDATE")
    var rlpuserupdate: String? = null

    @SerializedName("RLP_LASTUPDATE")
    var rlplastupdate: String? = null

    @SerializedName("RLP_NAMA")
    var rlpnama: String? = null

    override fun toString(): String {
        return "Kegiatan{" +
                "rLP_KODE = '" + rlpkode + '\''.toString() +
                ",rLP_ISAKTIF = '" + rlpisaktif + '\''.toString() +
                ",rLP_USERUPDATE = '" + rlpuserupdate + '\''.toString() +
                ",rLP_LASTUPDATE = '" + rlplastupdate + '\''.toString() +
                ",rLP_NAMA = '" + rlpnama + '\''.toString() +
                "}"
    }
}