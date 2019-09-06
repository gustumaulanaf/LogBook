package com.gustu.logbook.login.model

import com.google.gson.annotations.SerializedName

class LoginResponse {

    @SerializedName("0")
    var jsonMember0: JsonMember0? = null

    @SerializedName("isLogin")
    var isLogin: Int = 0

    @SerializedName("SIP")
    var sip: List<Any>? = null

    @SerializedName("menu")
    var menu: List<Any>? = null

    @SerializedName("SPK")
    var spk: List<Any>? = null

    override fun toString(): String {
        return "LoginResponse{" +
                "0 = '" + jsonMember0 + '\''.toString() +
                ",isLogin = '" + isLogin + '\''.toString() +
                ",sIP = '" + sip + '\''.toString() +
                ",menu = '" + menu + '\''.toString() +
                ",sPK = '" + spk + '\''.toString() +
                "}"
    }
}