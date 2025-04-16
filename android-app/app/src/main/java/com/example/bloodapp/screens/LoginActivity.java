package com.example.bloodapp.screens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bloodapp.R;
import com.example.bloodapp.api.ApiClient;
import com.example.bloodapp.api.ApiService;
import com.example.bloodapp.models.User;
import com.example.bloodapp.models.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText emailInput, passwordInput;
    Button loginBtn, goToRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.login_email);
        passwordInput = findViewById(R.id.login_password);
        loginBtn = findViewById(R.id.login_button);
        goToRegisterBtn = findViewById(R.id.login_to_register_button);

        loginBtn.setOnClickListener(v -> loginUser());
        goToRegisterBtn.setOnClickListener(v -> startActivity(new Intent(this, RegisterActivity.class)));
    }

    void loginUser() {
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        User loginUser = new User(email, password);
        ApiService api = ApiClient.getClient(this).create(ApiService.class);

        api.login(loginUser).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if (response.isSuccessful() && loginResponse != null) {
                    SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("token", loginResponse.getToken());
                    editor.putString("name", loginResponse.getName());
                    editor.putString("email", loginResponse.getEmail());
                    editor.apply();

                    Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid credentials or user not found", Toast.LENGTH_SHORT).show();
                    Log.e("LOGIN_FAIL", "Response error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Login failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("LOGIN_DEBUG", "Login error: " + t.getMessage(), t);
            }
        });
    }
}
