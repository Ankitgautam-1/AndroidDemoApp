package com.example.demoapp;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.demoapp.home.adapters.ProductAdapter;
import com.example.demoapp.home.adapters.TodoListAdapters;
import com.example.demoapp.utils.model.ProductListModel;
import com.example.demoapp.utils.model.Todo;
import com.example.demoapp.utils.network.ApiClient;
import com.example.demoapp.utils.services.ProductServices;
import com.example.demoapp.utils.services.TestingServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
    }

    @Override
    public void onStart() {
//        getTodoList();
        getProductsList();
        super.onStart();
    }


    private void getProductsList(){
        ListView listView = (ListView) getView().findViewById(R.id.todos);
        Log.d("HomeFragment", "listView:"+listView);
        ProgressBar progressBar = (ProgressBar) getView().findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        ProductServices productServices = ApiClient.getClient().create(ProductServices.class);
        productServices.getProducts().enqueue(new Callback<ProductListModel>() {
            @Override
            public void onResponse(Call<ProductListModel> call, Response<ProductListModel> response) {
                Log.d("HomeFragment", "Response:" + response.body() );
                if(response.isSuccessful() && response.body()!=null){
                    progressBar.setVisibility(View.GONE);
                    response.body().products.forEach( todo->{
                        ProductAdapter todoListAdapters = new ProductAdapter( getActivity(), response.body());
                        listView.setAdapter(todoListAdapters);
                    });

                }else{
                    progressBar.setVisibility(View.GONE);
                    Log.d("HomeFragment", "Error:" +response.message() );
                }
                            }

            @Override
            public void onFailure(Call<ProductListModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);

                Log.d("HomeFragment", "Error:" +t.getMessage() );

            }
        });


    }
//    private void getTodoList(){
//        ListView listView = (ListView) getView().findViewById(R.id.todos);
//        Log.d("HomeFragment", "listView:"+listView);
//            ProgressBar progressBar = (ProgressBar) getView().findViewById(R.id.progress);
//            progressBar.setVisibility(View.VISIBLE);
//            TestingServices testingApiService = ApiClient.getClient().create(TestingServices.class);
//            testingApiService.getTodoList().enqueue(new Callback<List<Todo>>() {
//                @Override
//                public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
//                    Log.d("HomeFragment", "Response:" + response.body() );
//                    if(response.isSuccessful() && response.body()!=null){
//                        progressBar.setVisibility(View.GONE);
//                       response.body().forEach( todo->{
//                           TodoListAdapters todoListAdapters = new TodoListAdapters( getActivity(), response.body());
//                           listView.setAdapter(todoListAdapters);
//                       });
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<List<Todo>> call, Throwable t) {
//                    progressBar.setVisibility(View.GONE);
//                    Log.d("HomeFragment", "Error:" +t.getMessage() );
//
//                }
//            });
//
//        }
}