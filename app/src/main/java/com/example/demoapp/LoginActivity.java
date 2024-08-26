package com.example.demoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.demoapp.home.HomeActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        final EditText emailEditText = findViewById(R.id.email_text_edit);
        final EditText password = findViewById(R.id.password_text_edit);

        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final boolean isEmailSame=emailEditText.getText().toString().equals("abc@test.com");
                    final boolean isPasswordSame=password.getText().toString().equals("test123");
                if(!isEmailSame){
                    Toast.makeText(LoginActivity.this, "Invalid Email", Toast.LENGTH_LONG).show();
                    System.out.println("Invalid Email");
                }else{
                    if(!isPasswordSame){
                        System.out.println("Invalid password");

                        Toast.makeText(LoginActivity.this, "Invalid Password", Toast.LENGTH_LONG).show();
                    }
                }
                if(isEmailSame && isPasswordSame){
                    SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_pref), MODE_PRIVATE);
                    sharedPreferences.edit().putString(getString(R.string.email_key),"abc@test.com").apply();

                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                    Intent homePageIntent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(homePageIntent);
                    finish();
                }
            }
        });
    }
}