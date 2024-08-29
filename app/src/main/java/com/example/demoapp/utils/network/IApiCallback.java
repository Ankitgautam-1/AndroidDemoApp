package com.example.demoapp.utils.network;

import okhttp3.ResponseBody;
import retrofit2.Response;

public interface IApiCallback {
    public void onResponseReceived(ResponseBody responseBody, String apiName);
    public void onError(Exception ex,String apiName);
    public void onParserFailure(String apiName);
}