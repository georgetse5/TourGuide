package com.example.tourguide;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ApiRequests {

    private static HttpURLConnection connection;
    private static int count = 0;
    private static int shopParser=0;

    public static void api_requests(String[] args){
        ArrayList<String> requests = new ArrayList<String>();
        requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.088904%2C23.546338&radius=5000&type=restaurant&key=AIzaSyCCxiS4m7nTl9UZTj9XqS5ACqEIUMIpOfg");
        requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.088904%2C23.546338&radius=5000&type=cafe&key=AIzaSyCCxiS4m7nTl9UZTj9XqS5ACqEIUMIpOfg");
        requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.088904%2C23.546338&radius=5000&type=museum&key=AIzaSyCCxiS4m7nTl9UZTj9XqS5ACqEIUMIpOfg");
        /*for (int i=0;i<requests.size();i++) {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(requests.get(i))).build();
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(ApiRequests::parse)
                    .join(); */
        for (int i=0;i<requests.size();i++) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(requests.get(i)).build();
            try {
                Response response = client.newCall(request).execute();
                // Handle the response here
                String responseBody = response.body().string();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
