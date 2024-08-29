package com.example.demoapp.utils.network;

import android.content.SharedPreferences;
import android.util.Log;
;import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.demoapp.utils.model.Todo;
import com.google.gson.Gson;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiExecuter {
    private static final String ApiExecuter = "ApiExecuter";
    SharedPreferences sharedPreferences;
    String reqBody;
    public IApiCallback iApiCallback;
    public void execute(Call call, String reqBody, IApiCallback callback, String apiName, @Nullable SharedPreferences sharedPreferences ) {
        try{
            Log.d(ApiExecuter,"Request:url:"+apiName);
            Log.d(ApiExecuter,"Request:header:"+call.request().headers());
            Log.d(ApiExecuter,"Request:method:"+call.request().method());
        }catch(Exception e){
            Log.d(ApiExecuter,"Exception: Request:url:"+apiName +"Exception:"+e);

        }
        this.iApiCallback=callback;

        try{
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    try {
                        Log.d(ApiExecuter,"Request:url api executer:"+apiName+" Response:"+response);
                        if(response.isSuccessful()&& response.body()!=null){
                            String str=response.body().toString();
                            Log.d(ApiExecuter,"Request:url:"+apiName+" \nResponse:"+response.body());

                            iApiCallback.onResponseReceived(response.body(),apiName);
                        }else{
                            Log.d(ApiExecuter,"Request:url:"+apiName+"Error:"+response.body().toString());
                            Exception exception= new Exception(response.body().toString());
                            iApiCallback.onError(exception,apiName);
                            Log.d(ApiExecuter,"Request:url:"+apiName+"Exception:"+exception);
                        }
                    }catch(Exception e){
                        Log.d(ApiExecuter,"Request:url:"+apiName+"Error:"+response.body().toString());

                        iApiCallback.onError(e,apiName);

                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d(ApiExecuter,"Request:Exception:url:"+apiName+"Error:"+t.toString());

                    iApiCallback.onError((Exception) t,apiName);

                }
            });
        }catch(Exception e){
            Log.d(ApiExecuter,"Request:Exception:url:"+apiName+"Error:"+e.toString());

            iApiCallback.onError(e,apiName);
        }
    }

}
