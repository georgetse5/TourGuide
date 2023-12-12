package com.example.tourguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActivityTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        List<Item> items = new ArrayList<Item>();
        items.add(new Item("Ελλήνων Γέυσεις","2321 098808",R.drawable.restaurant));
        items.add(new Item("Μαντάμ","2321 401366",R.drawable.restaurant));
        items.add(new Item("Κάππαρη","2321 020066",R.drawable.restaurant));
        items.add(new Item("Αντάμωμα","2321 071533",R.drawable.restaurant));
        items.add(new Item("Το καπηλειό του Κωστή","2321 058288",R.drawable.restaurant));
        items.add(new Item("Καθ'οδον","2321 023290",R.drawable.restaurant));
        items.add(new Item("Souvlaki Vlaxos"," 2321 058039",R.drawable.restaurant));
        items.add(new Item("Σουβλάκι Σώτος","2321 056211",R.drawable.restaurant));
        items.add(new Item("Family Kitchen","2321 022858",R.drawable.restaurant));
        items.add(new Item("Κουζίνα","2321 181000",R.drawable.restaurant));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Log.d("ActivityTwo", "Number of items in the list: " + items.size());


        recyclerView.setAdapter(new MyAdapter(this, items));

    }

}