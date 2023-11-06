package com.example.tourguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ActivityOne extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap MyMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        MyMap = googleMap;

        LatLng sydney = new LatLng(41.0745235750874, 23.553951511475475);
        MyMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Marker in Serres"));
        MyMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }
}