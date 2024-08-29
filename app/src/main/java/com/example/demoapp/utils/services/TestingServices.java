package com.example.demoapp.utils.services;

import com.example.demoapp.utils.model.Todo;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TestingServices {

    @GET("/todos/1")
    Call<Todo> getTodo();

    @GET("/todos")
    Call<List<Todo>> getTodoList();
}



