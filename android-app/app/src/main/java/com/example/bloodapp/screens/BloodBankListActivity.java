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

public class BloodBankListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BloodBankAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank_list); // reuse the same or create new layout

        recyclerView = findViewById(R.id.blood_bank_recycler);// reuse the same ID or change it in XML
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        try {
            List<BloodBank> banks = CsvUtils.readBloodBanksFromAssets(this, "blood-banks.csv");;
            adapter = new BloodBankAdapter(banks);
            recyclerView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error loading blood bank data", Toast.LENGTH_LONG).show();
        }
    }
}
