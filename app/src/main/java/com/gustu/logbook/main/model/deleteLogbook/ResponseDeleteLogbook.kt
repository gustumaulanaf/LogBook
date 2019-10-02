package com.gustu.logbook.main.model.deleteLogbook

import org.simpleframework.xml.Element

class ResponseDeleteLogbook {
    @field:Element(name = "status")
    var status:String? = null
    @field:Element(name = "request")
    var request:Int? = null

    override fun toString(): String {
        return "ResponseDeleteLogbook(status=$status, request=$request)"
    }

}