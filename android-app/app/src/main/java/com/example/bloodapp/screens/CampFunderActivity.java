// CampFunderActivity.java
package com.example.bloodapp.screens;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.bloodapp.R;

public class CampFunderActivity extends AppCompatActivity {

    EditText funderNameInput, amountInput;
    Button submitFunderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_funder);

        funderNameInput = findViewById(R.id.funder_name);
        amountInput = findViewById(R.id.funding_amount);
        submitFunderBtn = findViewById(R.id.submit_funder_btn);

        submitFunderBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Camp Funder Registered", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}