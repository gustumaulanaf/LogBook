package com.gustu.logbook.main.model.levelKesulitan

import com.google.gson.annotations.SerializedName

class Kesulitan {

    @SerializedName("RLK_KODE")
    var rlkkode: String? = null

    @SerializedName("RLK_NAMA")
    var rlknama: String? = null

    @SerializedName("RLK_USERUPDATE")
    var rlkuserupdate: String? = null

    @SerializedName("RLK_ISAKTIF")
    var rlkisaktif: String? = null

    @SerializedName("RLK_LASTUPDATE")
    var rlklastupdate: String? = null

    override fun toString(): String {
        return "Kegiatan{" +
                "rLK_KODE = '" + rlkkode + '\''.toString() +
                ",rLK_NAMA = '" + rlknama + '\''.toString() +
                ",rLK_USERUPDATE = '" + rlkuserupdate + '\''.toString() +
                ",rLK_ISAKTIF = '" + rlkisaktif + '\''.toString() +
                ",rLK_LASTUPDATE = '" + rlklastupdate + '\''.toString() +
                "}"
    }
}