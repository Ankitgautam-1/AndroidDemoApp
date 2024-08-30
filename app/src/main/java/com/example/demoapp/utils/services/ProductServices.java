package com.example.demoapp.utils.services;

import com.example.demoapp.utils.model.ProductListModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductServices  {

    @GET("/products")
    Call<ProductListModel> getProducts();
}
