package com.example.tourguide;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.libraries.places.api.Places;
import android.os.Bundle;
import android.util.Log;
import android.os.Environment;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ActivityTwo extends AppCompatActivity {
    private static final String TAG = "ActivityTwo";
    private PlacesClient placesClient;
    private JSONArray placesArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        Places.initialize(getApplicationContext(),"testing");
        placesClient = Places.createClient(this);

    }

}