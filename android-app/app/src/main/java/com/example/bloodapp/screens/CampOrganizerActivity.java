// CampOrganizerActivity.java
package com.example.bloodapp.screens;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.bloodapp.R;

public class CampOrganizerActivity extends AppCompatActivity {

    EditText organizerNameInput, eventDateInput;
    Button submitOrganizerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_organizer);

        organizerNameInput = findViewById(R.id.organizer_name);
        eventDateInput = findViewById(R.id.organizer_event_date);
        submitOrganizerBtn = findViewById(R.id.submit_organizer_btn);

        submitOrganizerBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Camp Organizer Registered", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}