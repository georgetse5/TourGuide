package com.example.tourguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ActivityTwo extends AppCompatActivity {

    ArrayList<ExampleModel> exampleModels = new ArrayList<>();

    int[] exampleImages = {R.drawable.baseline_restaurant_24};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        RecyclerView recyclerView = findViewById(R.id.rRecyclerView);
        setUpExampleModel();

        E_RecyclerViewAdapter adapter = new E_RecyclerViewAdapter(this,
                exampleModels);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void setUpExampleModel(){
        String[] exampleNames = getResources().getStringArray(R.array.example_txt);
        String[] exampleAbbreviation = getResources().getStringArray(R.array.example_txt);
        String[] exampleAbbreviationSmall = getResources().getStringArray(R.array.example_txt);

        for (int i = 0; i<exampleNames.length; i++){
            ExampleModel.add(new ExampleModel(exampleNames[i],
                    exampleAbbreviation[i],
                    exampleAbbreviationSmall[i],
                    exampleImages[i]));
        }
    }
}