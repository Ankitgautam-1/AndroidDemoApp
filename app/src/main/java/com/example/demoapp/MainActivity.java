package com.example.demoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.demoapp.home.HomeActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        moveToLoginScreen();
    }

    protected  void moveToLoginScreen(){
        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_pref), MODE_PRIVATE);
               final String email = sharedPreferences.getString(getString(R.string.email_key),null);

                if(email != null){
                    Intent homePageIntent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(homePageIntent);
                    finish();
                }else{
                    Intent loginPageIntent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(loginPageIntent);
                    finish();
                }

            }
        },3000);
    }
}