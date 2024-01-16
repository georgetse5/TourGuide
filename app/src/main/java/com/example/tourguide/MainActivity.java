package com.example.tourguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;

    // Declare your fragments
    private ExploreFragment exploreFragment;
    private RestaurantsFragment restaurantsFragment;
    private SightsFragment sightsFragment;
    private FavoritesFragment favoritesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.ActivityOne);
        button2 = findViewById(R.id.ActivityTwo);
        button3 = findViewById(R.id.ActivityThree);
        button4 = findViewById(R.id.ActivityFour);
        button5 = findViewById(R.id.About);

        // Initialize your fragments
        exploreFragment = new ExploreFragment();
        restaurantsFragment = new RestaurantsFragment();
        sightsFragment = new SightsFragment();
        favoritesFragment = new FavoritesFragment();

        // Your existing code...

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set listener for item selection
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle item selection here
                int itemId = item.getItemId();

                if (itemId == R.id.navigation_explore) {
                    loadFragment(exploreFragment);
                    return true;
                } else if (itemId == R.id.navigation_restaurants) {
                    loadFragment(restaurantsFragment);
                    return true;
                } else if (itemId == R.id.navigation_sights) {
                    loadFragment(sightsFragment);
                    return true;
                } else if (itemId == R.id.navigation_favorites) {
                    loadFragment(favoritesFragment);
                    return true;
                } else {
                    return false;
                }
            }
        });

        // Load the initial fragment
        loadFragment(exploreFragment);



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this, ExploreFragment.class);
                startActivity(intent);

            }
        });



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, RestaurantsFragment.class);
                startActivity(intent);
            }
        });



        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SightsFragment.class);
                startActivity(intent);
            }
        });



        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, FavoritesFragment.class);
                startActivity(intent);
            }
        });

        button5.setOnClickListener(new View.OnClickListener()  {

         @Override
            public void onClick(View v) {

             Intent intent = new Intent(MainActivity.this,ActivityFive.class);
             startActivity(intent);
         }

        });


    }

    // Helper method to load fragments
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);  // Optional: Add fragment to back stack
        transaction.commit();
    }
}