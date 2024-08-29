package com.example.demoapp.home;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.demoapp.R;
import com.example.demoapp.home.adapters.TodoListAdapters;
import com.example.demoapp.utils.model.Todo;
import com.example.demoapp.utils.network.ApiClient;
import com.example.demoapp.utils.services.TestingServices;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        getTodoList();

    }
        private void getTodoList(){
        ListView listView = findViewById(R.id.todoList);
            ProgressBar progressBar = findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);
            TestingServices testingApiService = ApiClient.getClient().create(TestingServices.class);
            testingApiService.getTodoList().enqueue(new Callback<List<Todo>>() {
                @Override
                public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                    Log.d("HomeActivity", "Response:" + response.body() );
                    if(response.isSuccessful() && response.body()!=null){
                        progressBar.setVisibility(View.GONE);
                       response.body().forEach( todo->{
                           TodoListAdapters todoListAdapters = new TodoListAdapters(getApplicationContext(), response.body());
                           listView.setAdapter(todoListAdapters);
                       });
                    }
                }

                @Override
                public void onFailure(Call<List<Todo>> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);

                }
            });

        }




//    private void setUpRefreshButton() {
//        Button home_refresh_button = findViewById(R.id.home_refresh_button);
//        home_refresh_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextView homeTextView = findViewById(R.id.home_view);
//                TestingServices testingApiService = ApiClient.getClient().create(TestingServices.class);
//                testingApiService.getTodo().enqueue(
//                        new Callback<Todo>() {
//                            @Override
//                            public void onResponse(Call<Todo> call, retrofit2.Response<Todo> response) {
//                                if (response.isSuccessful() && response.body() != null) {
//
//                                    homeTextView.setText("Response: " + response.body().toJson().toString());
//                                } else {
//                                    homeTextView.setText("Error :" + response.code());
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<Todo> call, Throwable t) {
//                                homeTextView.setText("Error :" + t.getMessage());
//
//                            }
//                        }
//                );
//
//            }
//        });
//    }
//
}