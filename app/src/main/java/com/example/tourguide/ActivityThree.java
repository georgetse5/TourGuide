package com.example.tourguide;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;

public class ActivityThree extends AppCompatActivity {

    public ArrayList<String> placesNames = new ArrayList<>();
    public ArrayList<Double> placesLat = new ArrayList<>();
    public ArrayList<Double> placesLong = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference placesRef = db.collection("places");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        loadPlaces();

//        Map<String, Object> places = new HashMap<>();
//        places.put("name", "Place");
//        places.put("latitude", "41.09114892507394");
//        places.put("longitude", "23.549864919543243");
//        places.put("description", "A test text for the place");
//
//        db.collection("places").add(places).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//            @Override
//            public void onSuccess(DocumentReference documentReference) {
//                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
//            }
//        });
        
    }

    public void loadPlaces() {

        TextView viewData = (TextView) findViewById(R.id.text_view_data);


        placesRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String data = "";
                        int counter = 0;
                      

                        data += "For testing purposes only\n=========================\n\n";

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Places places = documentSnapshot.toObject(Places.class);
                            counter +=1;

                            String name = places.getName();
                            String description = places.getDescription();
                            double latitude = places.getLatitude();
                            double longitude = places.getLongitude();

                            placesNames.add(name);
                            placesLat.add(latitude);
                            placesLong.add(longitude);

//                            data += "Name: " + name + "\nDescription: " + description + "\n\n";
                        }

                        for (int i = 0 ; i < placesNames.size() ; i++) {
                            data += "Name: " + placesNames.get(i) + "\nLatitude: " + placesLat.get(i) + "\nLongitude: " + placesLong.get(i) + "\n\n";
                        }

                        System.out.println(data);
                        System.out.println("Number of records:" + counter);
                        data += "Number of records: " + counter + "\n\n";
                        viewData.setText(data);

                        // Ta dedomena emfanizontai kanonika sto System.out.println(data) mesw toy logcat

                    }
                });
    }

}