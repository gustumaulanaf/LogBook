package com.gustu.logbook.main.model.deleteLogbook

import org.simpleframework.xml.Element

class ResponseDeleteLogbook {
    @field:Element(name = "status")
    var status:String? = null

    override fun toString(): String {
        return "ResponseDeleteLogbook(status=$status)"
    }
}