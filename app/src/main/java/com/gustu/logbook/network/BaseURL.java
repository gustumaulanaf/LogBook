package com.gustu.logbook.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class BaseURL {
    Retrofit retrofit = null;
    public API getAPI(){
        String BASE_URL = "http://10.1.1.116/instikp/webservices/index.php/logbook/Logbook_services/";
        if (retrofit==null){
            retrofit  = new Retrofit
                    .Builder()
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();

        }
        return retrofit.create(API.class);
    }
}
