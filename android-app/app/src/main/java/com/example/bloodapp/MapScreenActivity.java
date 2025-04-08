package com.example.bloodapp.screens;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import com.example.bloodapp.R;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

public class MapScreenActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private final LatLng defaultLocation = new LatLng(20.5937, 78.9629); // India center

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_screen);

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map_container);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 5));

        // Example marker (you'll fetch these from API later)
        map.addMarker(new MarkerOptions()
                .position(new LatLng(17.3850, 78.4867)) // Hyderabad
                .title("Donor: A+"));

        map.addMarker(new MarkerOptions()
                .position(new LatLng(12.9716, 77.5946)) // Bangalore
                .title("Request: B- Needed"));
    }
}
