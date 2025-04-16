package com.example.bloodapp.screens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bloodapp.R;

public class ProfileActivity extends AppCompatActivity {

    TextView profileName, profileEmail, profilePhone;
    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileName = findViewById(R.id.profile_name);
        profileEmail = findViewById(R.id.profile_email);
        profilePhone = findViewById(R.id.profile_phone);
        logoutButton = findViewById(R.id.logout_button);

        SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        String name = prefs.getString("name", "Unknown");
        String email = prefs.getString("email", "No email");
        String phone = prefs.getString("phone", "No phone");

        profileName.setText("Name: " + name);
        profileEmail.setText("Email: " + email);
        profilePhone.setText("Phone: " + phone);

        logoutButton.setOnClickListener(v -> {
            prefs.edit().clear().apply();
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
            finish(); // Or redirect to Login screen
        });
    }
}
