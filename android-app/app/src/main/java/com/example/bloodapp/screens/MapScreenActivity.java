package com.example.bloodapp.screens;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodapp.R;
import com.example.bloodapp.adapters.BloodBankAdapter;
import com.example.bloodapp.models.BloodBank;
import com.example.bloodapp.utils.CsvUtils;

import java.util.List;

public class MapScreenActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BloodBankAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_screen);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        try {
            List<BloodBank> bloodBanks = CsvUtils.readBloodBanksFromAssets(this, "blood-banks.csv");
            adapter = new BloodBankAdapter(bloodBanks);
            recyclerView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to load blood bank data", Toast.LENGTH_SHORT).show();
        }
    }
}
