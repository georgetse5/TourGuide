package com.example.tourguide;

import static com.google.firebase.firestore.FirebaseFirestore.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;


import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;



import java.util.ArrayList;
import java.util.Objects;

public class ActivityTwo extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<User> userArrayList;
    Myadapter Myadapter;
    FirebaseFirestore db;
    RelativeLayout relativeLayout;

    ProgressDialog progressDialog;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        progressDialog  = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();


        relativeLayout = findViewById(R.id.RecyclerView);
        recyclerView = relativeLayout.findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        userArrayList = new ArrayList<>();
        Myadapter = new Myadapter(ActivityTwo.this, userArrayList);

        recyclerView.setAdapter(Myadapter);

            EventChangeListener();


    }

    private void EventChangeListener() {
        db.collection("Users").orderBy("firstName", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null){

                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                            Log.e("Firestore error", Objects.requireNonNull(error.getMessage()));
                            return;

                        }
                        assert value != null;
                        for (DocumentChange dc : value.getDocumentChanges()){

                            if (dc.getType() == DocumentChange.Type.ADDED){
                                userArrayList.add(dc.getDocument().toObject(User.class));
                            }

                            Myadapter.notifyDataSetChanged();
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();


                        }

                    }
                });

    }

}
