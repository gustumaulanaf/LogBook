package com.gustu.logbook.main.model.addLogbook;

import com.google.gson.annotations.SerializedName;
import com.gustu.logbook.login.model.JsonMember0;

import org.simpleframework.xml.Element;

public class ResponseSaveLogbook {
    @Element(name = "status")
    private String status;

    @Element(name = "request")
    private int request;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRequest() {
        return request;
    }

    public void setRequest(int request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "ResponseSaveLogbook{" +
                "status='" + status + '\'' +
                ", request=" + request +
                '}';
    }
}
