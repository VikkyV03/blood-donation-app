package com.example.bloodapp.screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bloodapp.R;
import com.example.bloodapp.api.ApiClient;
import com.example.bloodapp.api.ApiService;
import com.example.bloodapp.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText nameInput, emailInput, passwordInput, phoneInput;
    Spinner roleSpinner;
    Button registerBtn, goToLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameInput = findViewById(R.id.register_name);
        emailInput = findViewById(R.id.register_email);
        passwordInput = findViewById(R.id.register_password);
        phoneInput = findViewById(R.id.register_phone);
        roleSpinner = findViewById(R.id.register_role_spinner);
        registerBtn = findViewById(R.id.register_button);
        goToLoginBtn = findViewById(R.id.register_to_login_button);

        String[] roles = {"donor", "hospital", "admin"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, roles);
        roleSpinner.setAdapter(adapter);

        registerBtn.setOnClickListener(v -> registerUser());
        goToLoginBtn.setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));
    }

    void registerUser() {
        User user = new User(
                nameInput.getText().toString(),
                emailInput.getText().toString(),
                passwordInput.getText().toString(),
                phoneInput.getText().toString(),
                roleSpinner.getSelectedItem().toString()
        );

        ApiService apiService = ApiClient.getClient(RegisterActivity.this).create(ApiService.class);
        Call<User> call = apiService.register(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Registration failed.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
