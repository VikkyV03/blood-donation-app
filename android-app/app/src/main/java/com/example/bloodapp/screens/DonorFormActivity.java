package com.example.bloodapp.screens;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.bloodapp.R;
import com.example.bloodapp.api.ApiClient;
import com.example.bloodapp.api.ApiService;
import com.example.bloodapp.models.Donor;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonorFormActivity extends AppCompatActivity {

    Spinner bloodTypeSpinner;
    EditText nameInput, phoneInput, cityInput, lastDonatedInput;
    Switch availabilitySwitch;
    Button submitButton;

    FusedLocationProviderClient fusedClient;
    double latitude = 0.0, longitude = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_form);
        TextView donorHeader = findViewById(R.id.donor_header);
        SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        String name = prefs.getString("name", "user");
        donorHeader.setText("Donor @" + name);

        // Bind views
        bloodTypeSpinner = findViewById(R.id.donor_bloodtype_spinner);
        nameInput = findViewById(R.id.donor_name);
        phoneInput = findViewById(R.id.donor_phone);
        cityInput = findViewById(R.id.donor_city);
        lastDonatedInput = findViewById(R.id.donor_last_donated);
        availabilitySwitch = findViewById(R.id.donor_available);
        submitButton = findViewById(R.id.donor_submit_button);

        // Setup spinner
        String[] bloodTypes = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, bloodTypes);
        bloodTypeSpinner.setAdapter(adapter);

        fusedClient = LocationServices.getFusedLocationProviderClient(this);
        getLocation();

        submitButton.setOnClickListener(v -> submitDonorForm());
    }

    void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            return;
        }

        fusedClient.getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            } else {
                Toast.makeText(this, "Unable to get location", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void submitDonorForm() {
        String name = nameInput.getText().toString().trim();
        String phone = phoneInput.getText().toString().trim();
        String bloodType = bloodTypeSpinner.getSelectedItem().toString();
        String city = cityInput.getText().toString().trim();
        String lastDonated = lastDonatedInput.getText().toString().trim();
        boolean available = availabilitySwitch.isChecked();

        if (name.isEmpty() || phone.isEmpty() || city.isEmpty()) {
            Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Donor donor = new Donor(name, phone, bloodType, city, lastDonated, available, latitude, longitude);

        ApiService api = ApiClient.getClient(this).create(ApiService.class);
        api.registerDonor(donor).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DonorFormActivity.this, "Donor registered!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(DonorFormActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(DonorFormActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] perms, @NonNull int[] results) {
        super.onRequestPermissionsResult(requestCode, perms, results);
        if (requestCode == 101 && results.length > 0 && results[0] == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        }
    }
}
