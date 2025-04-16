package com.example.bloodapp.screens;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.bloodapp.R;

public class RequestFormActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_form);
        Toast.makeText(this, "Request form coming soon!", Toast.LENGTH_SHORT).show();
    }
}
