package com.example.tourguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tourguide.ActivityFour;
import com.example.tourguide.ActivityOne;
import com.example.tourguide.ActivityThree;
import com.example.tourguide.ActivityTwo;
import com.example.tourguide.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ExampleModel> exampleModels = new ArrayList<>();

    int[] exampleImages = {R.drawable.baseline_restaurant_24};


    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.rRecyclerView);
        button1 = findViewById(R.id.ActivityOne);
        button2 = findViewById(R.id.ActivityTwo);
        button3 = findViewById(R.id.ActivityThree);
        button4 = findViewById(R.id.ActivityFour);
        button5 = findViewById(R.id.ActivityFive);
        setUpExampleModel();

        E_RecyclerViewAdapter adapter = new E_RecyclerViewAdapter(this,
                exampleModels);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));





        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this, ActivityOne.class);
                startActivity(intent);

            }
        });



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ActivityTwo.class);
                startActivity(intent);
            }
        });



        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ActivityThree.class);
                startActivity(intent);
            }
        });



        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ActivityFour.class);
                startActivity(intent);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ActivityFive.class);
                startActivity(intent);
            }
        });


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
