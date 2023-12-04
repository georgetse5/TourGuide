package com.example.tourguide;



import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ActivityFour extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<Object> viewItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG = "Favourites Activity";
    private  int count = 0;
    private  int shopParser = 0;
    private ArrayList<String> requests = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        apiCalls();
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerAdapter(this,viewItems);
        mRecyclerView.setAdapter(mAdapter);





        }

    private void apiCalls(){
        ArrayList<String> requests = new ArrayList<>();
        requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.088904%2C23.546338&radius=5000&type=restaurant&key=AIzaSyCCxiS4m7nTl9UZTj9XqS5ACqEIUMIpOfg");
        requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.088904%2C23.546338&radius=5000&type=cafe&key=AIzaSyCCxiS4m7nTl9UZTj9XqS5ACqEIUMIpOfg");
        requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.088904%2C23.546338&radius=5000&type=museum&key=AIzaSyCCxiS4m7nTl9UZTj9XqS5ACqEIUMIpOfg");
        for (int i=0;i<requests.size();i++) {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, requests.get(i), null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String name = response.getString("name");
                        String type = response.getString("type");
                        String rating = response.getString("rating");
                        String vicinity = response.getString("vicinity");
                        Sights sight = new Sights(name,vicinity,rating,type);
                        viewItems.add(sight);
                        //parseJson(response.toString());


                    }catch (Exception e) {

                    }}}, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            Volley.newRequestQueue(this).add(request);
        }


    }
    private void addItemsFromJSON(){
        try {
        String jsonDataString = readJSONDataFromFile();
        JSONArray jsonArray = new JSONArray(jsonDataString);
        for (int i=0; i<jsonArray.length(); ++i) {

            JSONObject itemObj = jsonArray.getJSONObject(i);

            String name = itemObj.getString("name");
            String vicinity = itemObj.getString("vicinity");
            String rating = itemObj.getString("rating");
            String type = itemObj.getString("type");

            Sights sight = new Sights(name,vicinity,rating,type);
            viewItems.add(sight);
        }
    } catch (JSONException | IOException e) {
            Log.d(TAG, "addItemsFromJSON: ", e);
        }
    }
    /*private String readJSONDataFromFile() throws IOException{

        InputStream inputStream = getResources().openRawResource(R.raw.sights);
        StringBuilder builder = new StringBuilder();

        try {

            String jsonString = null;
            inputStream = getResources().openRawResource(R.raw.sights);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));

            while ((jsonString = bufferedReader.readLine()) != null) {
                builder.append(jsonString);
            }

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(builder);
    }*/
    private String readJSONDataFromFile() throws IOException {
        StringBuilder builder = new StringBuilder();

        try (InputStream inputStream = getResources().openRawResource(R.raw.sights);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {

            String jsonString;
            while ((jsonString = bufferedReader.readLine()) != null) {
                builder.append(jsonString);
            }

        }

        return builder.toString();
    }
    private void parseJson(String responseBody) throws JSONException {
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        count = count + 1;
        int n = jsonArray.length();
        for (int i = 0; i < n; i++) {
            JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
            String business_status = (String) jsonObject1.get("business_status");
            if(business_status.equals("OPERATIONAL")) {
                shopParser+=1;
                String name = (String) jsonObject1.get("name");
                String type = (String) jsonObject1.get("type");
                String rating = (String) jsonObject1.get("rating");
                String vicinity = (String) jsonObject1.get("vicinity");
                Sights sight = new Sights(name,vicinity,rating,type);
                viewItems.add(sight);
                Log.d(TAG, String.valueOf(shopParser));
            }
        }
    }
}
