package com.example.tourguide;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourguide.Item;
import com.example.tourguide.MyAdapter;
import com.example.tourguide.R;
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

public class ActivityThree extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        List<Item> items = new ArrayList<Item>();
        items.add(new Item("Αυτοκινητοδρόμιο Σερρών","Πίστα αγώνων",R.drawable.sights));
        items.add(new Item("Αρχαιολογικό Μουσείο Σερρών (Μπεζεστένι)","Μουσείο",R.drawable.sights));
        items.add(new Item("Λαογραφικό μουσείο Καρακατσάνων","Μουσείο",R.drawable.sights));
        items.add(new Item("Ζιντζιρλί Τζαμί","Τζαμί",R.drawable.sights));
        items.add(new Item("Κοιλάδα Αγίων Αναργύρων","Αξιοθέατο",R.drawable.sights));
        items.add(new Item("Χιονοδρομικό κέντρο Λαϊλιά","Αξιοθέατο",R.drawable.sights));
        items.add(new Item("Ακρόπολη Σερρών (Cityzen)","Αξιοθέατο",R.drawable.sights));
        items.add(new Item("Ιερά Μονή Τιμίου Προδρόμου","Εκκλησία",R.drawable.sights));
        items.add(new Item("Γήπεδο Πανσερραϊκού","Αξιοθέατο",R.drawable.sights));
        items.add(new Item("Λουτρά σιδηροκάστρου","Ιαματικά Λουτρά",R.drawable.sights));



        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Log.d("ActivityThree", "Number of items in the list: " + items.size());


        recyclerView.setAdapter(new MyAdapter(this, items));


    }

}