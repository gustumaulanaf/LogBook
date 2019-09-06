package com.gustu.logbook.main.model.addLogbook

import com.google.gson.annotations.SerializedName
import com.gustu.logbook.login.model.JsonMember0

import org.simpleframework.xml.Element

class ResponseSaveLogbook {
   @field:Element(name = "status")
    var status: String? = null

   @field:Element(name = "request")
    var request: Int = 0

    override fun toString(): String {
        return "ResponseSaveLogbook{" +
                "status='" + status + '\''.toString() +
                ", request=" + request +
                '}'.toString()
    }
}
