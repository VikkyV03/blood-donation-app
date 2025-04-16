package com.example.bloodapp.screens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodapp.R;
import com.example.bloodapp.adapters.BloodBankAdapter;
import com.example.bloodapp.models.BloodBank;
import com.example.bloodapp.utils.CsvUtils;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private TextView welcomeText;
    private Button btnRequest, btnDonate, btnSearch, btnProfile, btnOrganizers, btnFunders;
    private EditText searchCity, searchState;
    private RecyclerView recyclerView;
    private BloodBankAdapter adapter;
    private List<BloodBank> allBanks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize views
        welcomeText = findViewById(R.id.welcome_text);
        btnRequest = findViewById(R.id.btn_request);
        btnDonate = findViewById(R.id.btn_donate);
        btnSearch = findViewById(R.id.btn_search);
        btnProfile = findViewById(R.id.btn_profile);
        btnOrganizers = findViewById(R.id.btn_organizers);
        btnFunders = findViewById(R.id.btn_funders);
        searchCity = findViewById(R.id.search_city);
        searchState = findViewById(R.id.search_state);
        recyclerView = findViewById(R.id.blood_bank_recycler);

        // Welcome message
        SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        String name = prefs.getString("name", "User");
        welcomeText.setText("Welcome, " + name);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        allBanks = CsvUtils.readBloodBanksFromAssets(this, "blood-banks.csv");
        adapter = new BloodBankAdapter(allBanks);
        recyclerView.setAdapter(adapter);

        // Search
        btnSearch.setOnClickListener(v -> performSearch());

        // Navigation
        btnRequest.setOnClickListener(v -> startActivity(new Intent(this, RequestFormActivity.class)));
        btnDonate.setOnClickListener(v -> startActivity(new Intent(this, DonorFormActivity.class)));
        btnProfile.setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));
        btnOrganizers.setOnClickListener(v -> startActivity(new Intent(this, CampOrganizerActivity.class)));
        btnFunders.setOnClickListener(v -> startActivity(new Intent(this, CampFunderActivity.class)));
    }

    private void performSearch() {
        String city = searchCity.getText().toString().trim().toLowerCase();
        String state = searchState.getText().toString().trim().toLowerCase();

        List<BloodBank> filtered = new ArrayList<>();
        for (BloodBank bank : allBanks) {
            boolean matchesCity = city.isEmpty() || bank.getCity().toLowerCase().contains(city);
            boolean matchesState = state.isEmpty() || bank.getState().toLowerCase().contains(state);
            if (matchesCity && matchesState) {
                filtered.add(bank);
            }
        }

        if (filtered.isEmpty()) {
            Toast.makeText(this, "No blood banks found for the given search.", Toast.LENGTH_SHORT).show();
        }

        adapter.updateData(filtered);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_logout) {
            SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
            prefs.edit().clear().apply();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return true;
        } else if (item.getItemId() == R.id.menu_profile) {
            startActivity(new Intent(this, ProfileActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
