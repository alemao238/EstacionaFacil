package com.example.junio.estacionafacil.service;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Created by junio on 22/06/16.
 */
public class ParkingRestClient {
    private AsyncHttpClient client=new AsyncHttpClient();
    private final String BASE_URL="https://obscure-brushlands-56218.herokuapp.com/api/v1";

    public void get(String url, AsyncHttpResponseHandler responseHandler){
        client.get(this.getAbsoluteURL(url),responseHandler);
    }

    public String getAbsoluteURL(String url){
        return BASE_URL+url;
    }
}
