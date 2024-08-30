package com.example.demoapp.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.window.OnBackInvokedDispatcher;

import androidx.activity.BackEventCompat;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentOnAttachListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.demoapp.HomeFragment;
import com.example.demoapp.ProfileFragment;
import com.example.demoapp.R;
import com.example.demoapp.SettingsFragment;
import com.example.demoapp.databinding.HomeActivityBinding;
import com.example.demoapp.home.adapters.TodoListAdapters;
import com.example.demoapp.utils.model.Todo;
import com.example.demoapp.utils.network.ApiClient;
import com.example.demoapp.utils.services.TestingServices;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    HomeActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);


//        getTodoList();

        setUpBottomNavigationBar();
    }
    private void setUpBottomNavigationBar(){
        binding = HomeActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceCurrentFragment(new HomeFragment(),1);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationBar);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId()==R.id.home){
                replaceCurrentFragment(new HomeFragment(),0);
            }else if(item.getItemId()==R.id.profile){
                replaceCurrentFragment(new ProfileFragment(),0);
            }else if(item.getItemId()==R.id.settings){
                replaceCurrentFragment(new SettingsFragment(),0);
            }
            return true;
        });


    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationBar);
        Log.d("HomeActivity", "onBackPressed:"+  bottomNavigationView.getSelectedItemId());

        if(bottomNavigationView.getSelectedItemId()==R.id.home){
            finish();
        }else{
            bottomNavigationView.setSelectedItemId(R.id.home);
        }

    }

    private void replaceCurrentFragment(Fragment fragment, int flag ){

        FragmentManager fragmentManager =getSupportFragmentManager();
     FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
       fragmentTransaction.replace(R.id.mainScreen, fragment);
       fragmentTransaction.commit();

    };





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