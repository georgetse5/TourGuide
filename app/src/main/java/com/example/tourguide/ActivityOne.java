package com.example.tourguide;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceTypes;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ActivityOne extends AppCompatActivity implements OnMapReadyCallback {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private final String TAG = "Map Activity";
    private GoogleMap MyMap;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private FusedLocationProviderClient mFusedLocationClient;
    public LatLng lat ;
    public LatLng longi;
    private LatLng currentloc;
//    private LatLng testingCurrentLocation = new LatLng(41.09113937409494, 23.550021265102966);
    private LatLng testing = new LatLng(41.084666328,23.543164494);

    // Edw apothikeyontai ta dedomena ths methodou fetchDataFromFirestore()
    public HashMap<String, Object> dataMap = new HashMap<>();

    public String placeName;
    private String query;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Places.initialize(getApplicationContext(),"AIzaSyAWo9aSdWkspvZMFeeWMU7WKRhPNCyPqxY");
        PlacesClient placesClient = Places.createClient(this);

        fetchDataFromFirestore();

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                System.out.println("onLocationChanged called!!!");
                // Use the location data as needed
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                // Handle location provider status changes
            }

            @Override
            public void onProviderEnabled(String provider) {
                // Handle when the location provider is enabled
            }

            @Override
            public void onProviderDisabled(String provider) {
                // Handle when the location provider is disabled
            }
        };

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        } else {
            // Request location permissions if not granted
            requestLocationPermission();
        }

        AutocompleteSupportFragment autocompleteSupportFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
 
        autocompleteSupportFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG, Place.Field.PLUS_CODE, Place.Field.PHONE_NUMBER, Place.Field.TYPES));
        autocompleteSupportFragment.setTypeFilter(TypeFilter.ESTABLISHMENT);

        autocompleteSupportFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onError(@NonNull Status status) {
                Log.i(TAG, "An error occured:" + status);
            }

            @Override
            public void onPlaceSelected(@NonNull Place place) {
                Log.i(TAG, "Place:" + place.getName() + ", " + place.getAddress()+ ", " + place.getPhoneNumber()+ ", " + place.getPlaceTypes());



                List<String> testTypes;
                String type = "";

                placeName = place.getName();
                System.out.println("Name: " + placeName);
                System.out.println("Address: " + place.getAddress());
                LatLng loc = place.getLatLng();
                System.out.println("Lat_Lng: " + place.getLatLng());
                System.out.println("Plus code: " + place.getPlusCode());
                System.out.println("Types: " + place.getPlaceTypes());

//                String targetString = "food";
                testTypes = place.getPlaceTypes();
//                if (testTypes.contains("food")) {
//                    type = "food";
//                } else if (testTypes.contains("museum")) {
//                    type = "museum";
//                } else {
//                    type = "Undefined";
//                }

                String paddress = place.getAddress();
                String pid = place.getId();
                String pname = place.getName();
                double lati = loc.latitude;
                double longit = loc.longitude;
                String pPhone = place.getPhoneNumber();

                if (addressContainsSerres(paddress)) {
                    pinSelectedMarker(loc, pname);
                }


                FirebaseFirestore db = FirebaseFirestore.getInstance();
                Query query = db.collection("places_from_api").whereEqualTo("PlaceID", pid);

                query.get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Αν η λίστα δεν είναι κενή, υπάρχει ήδη εγγραφή με αυτό το placeId
                        if (!task.getResult().isEmpty()) {
                            // Εδώ μπορείτε να το αντιμετωπίσετε όπως εσείς θέλετε
                            // Π.χ., εμφανίστε ένα μήνυμα λάθους, εκτελέστε κάποια ενέργεια, κ.λπ.
                            Toast.makeText(getApplicationContext(), "This placeId already exists into the database", Toast.LENGTH_SHORT).show();
                        } else {
                            // Δεν υπάρχει εγγραφή με αυτό το placeId, θα κάνει προσθήκη της εγγραφής
                            savePlaceToDatabase(pid, pname, paddress, lati, longit, testTypes, pPhone);
                        }
                    } else {
                        // Αν υπάρξει σφάλμα κατά την εκτέλεση του ερωτήματος
                        Toast.makeText(getApplicationContext(), "An error occurred with this placeId.", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.PHONE_NUMBER, Place.Field.TYPES, Place.Field.LAT_LNG, Place.Field.PLUS_CODE);

        // Start the autocomplete intent.
        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                .build(this);
        startAutocomplete.launch(intent);
       AutocompleteSessionToken token = AutocompleteSessionToken.newInstance();


       RectangularBounds bounds = RectangularBounds.newInstance(new LatLng(41.06104388668157, 23.502687403900623),
                                                                new LatLng(41.108726881157594, 23.57402786885185));


       FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
                // Call either setLocationBias() OR setLocationRestriction().

                .setLocationBias(bounds)
//                .setLocationRestriction(bounds)
                .setOrigin(currentloc)

                .setCountries("GR")
                .setTypesFilter(Arrays.asList(PlaceTypes.ADDRESS))
                .setSessionToken(token)
                .setQuery("Greece")
                .build();

        System.out.println("Bounds: " + bounds);

        placesClient.findAutocompletePredictions(request).addOnSuccessListener((response) -> {
            for (AutocompletePrediction prediction : response.getAutocompletePredictions()) {
                Log.i(TAG, prediction.getPlaceId());
                Log.i(TAG, prediction.getFullText(null).toString());
            }
        }).addOnFailureListener((exception) -> {
            if (exception instanceof ApiException) {
                ApiException apiException = (ApiException) exception;
                Log.e(TAG, "Place not found: " + apiException.getStatusCode());
            }
        });

    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        MyMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);

            MyMap.getUiSettings().setZoomControlsEnabled(true);

//            pinLocations(googleMap);
        }

    }

    protected void onDestroy() {
        super.onDestroy();
        // Remove location updates to conserve battery life
        if (locationManager != null && locationListener != null) {
            locationManager.removeUpdates(locationListener);
        }
    }

    private boolean checkLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        } else {
            // Permissions are granted automatically on versions below M
            return true;
        }
    }

    private void requestLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Location permission granted
                // You can proceed with location-related tasks
                if (MyMap != null) {
                    if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    MyMap.setMyLocationEnabled(true);
                } else {
                    // Location permission denied
                    // Handle the denial, maybe show a message to the user
                }
            }
        }
    }

    // =============================  PIN LOCATIONS  ========================================

    private void pinLocations(@NonNull GoogleMap map) {

        LatLng test1 = new LatLng(41.074712, 23.553938);
        MarkerOptions markerOptions = new MarkerOptions().position(test1).title("TEI")
                .icon(bitmapDescriptor(getApplicationContext(), R.drawable.baseline_place_24));

        map.addMarker(markerOptions);

        LatLng test2 = new LatLng(41.091117, 23.549866);
        MarkerOptions markerOptions1 = new MarkerOptions().position(test2).title("Center")
                .icon(bitmapDescriptor(getApplicationContext(), R.drawable.baseline_place_24));

        map.addMarker(markerOptions1);

        LatLng test3 = new LatLng(41.09093836950161, 23.549360012910867);
        MarkerOptions markerOptions2 = new MarkerOptions().position(test3).title("Mpezesteni")
                .icon(bitmapDescriptor(getApplicationContext(), R.drawable.baseline_place_24));

        map.addMarker(markerOptions2);

    }

    // =========================  PIN SELECTED MARKER  ======================================

    void pinSelectedMarker(LatLng loc, String name) {

        MarkerOptions markerOptions10 = new MarkerOptions().position(loc).title(name)
                .icon(bitmapDescriptor(getApplicationContext(), R.drawable.baseline_place_24));

        MyMap.addMarker(markerOptions10);

        LatLng markerLatLng = markerOptions10.getPosition();
        MyMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerLatLng, 20));

    }


    // =========================  PIN FETCHED LOCATIONS  ======================================


//    void pinFetchedLocations(HashMap<String, Object> dataMap) {
//
//        System.out.println("pinFetchedLoacations called");
//        int size = dataMap.size();
//        System.out.println("dataMap size: " + size);
//
//        for (int entry=1; entry<=size; entry++) {
//            entry = (int) dataMap.get("Entry");
//            System.out.println("DataMap entry: " + entry);
//
//            String placeName = (String) dataMap.get("Name");
//            double latitude = (double) dataMap.get("Latitude");
//            double longitude = (double) dataMap.get("Longitude");
//
//            System.out.println(placeName);
//
//
//            LatLng loc = new LatLng(latitude, longitude);
//            System.out.println(loc);
//
//            MarkerOptions markerOptions = new MarkerOptions()
//                    .position(loc)
//                    .title(placeName)
//                    .icon(bitmapDescriptor(getApplicationContext(), R.drawable.baseline_place_24));
//
//            MyMap.addMarker(markerOptions);
//
//        }
//    }


//    void pinFetchedLocations(HashMap<String, Object> dataMap) {
//        System.out.println("pinFetchedLocations called");
//
//        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
//            String documentId = entry.getKey();
//            Map<String, Object> documentData = (Map<String, Object>) entry.getValue();
//
//            int entryNumber = (int) documentData.get("Entry");
//            System.out.println("DataMap Entry: " + entryNumber);
//
//            String placeName = (String) documentData.get("Name");
//            double latitude = (double) documentData.get("Latitude");
//            double longitude = (double) documentData.get("Longitude");
//
//            System.out.println("Name: " + placeName);
//
//            LatLng loc = new LatLng(latitude, longitude);
//            System.out.println("Location: " + loc);
//
//            MarkerOptions markerOptions = new MarkerOptions()
//                    .position(loc)
//                    .title(placeName)
//                    .icon(bitmapDescriptor(getApplicationContext(), R.drawable.baseline_place_24));
//
//            MyMap.addMarker(markerOptions);
//        }
//    }


    void pinFetchedLocations(LatLng loc, String name) {

        MarkerOptions markerOptions10 = new MarkerOptions().position(loc).title(name)
                .icon(bitmapDescriptor(getApplicationContext(), R.drawable.baseline_place_24));

        MyMap.addMarker(markerOptions10);

    }


    // ==========================  BITMAP DESCRIPTOR  =======================================

    private BitmapDescriptor bitmapDescriptor(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);

        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


    // ==========================  AUTOCOMPLETE  =======================================


    private final ActivityResultLauncher<Intent> startAutocomplete = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent intent = result.getData();
                    if (intent != null) {
                        Place place = Autocomplete.getPlaceFromIntent(intent);
                        Log.i(TAG, "Place: ${place.getName()}, ${place.getId()}");
                    }
                } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                    // The user canceled the operation.
                    Log.i(TAG, "User canceled autocomplete");
                }
            });

    // =======================  SAVE PLACE TO DATABASE  =====================================

    private void savePlaceToDatabase(String pid, String pname, String paddress, double lati, double longit, List<String> type, String pPhone){

        if (addressContainsSerres(paddress)) {

            String placeid = pid;
            String name = pname;
            String address = paddress;
            List<String> types = type;
            String phone = pPhone;
            double latitude = lati;
            double longitude = longit;

//        placeid = "fhHDJ3jFKJh45lf1ka";
//        name = "testPlace";
//        address = "Merarxias";
//        latitude = 41.09114892507394;
//        longitude = 23.549864919543243;
//        types= "Food";
//        phone = "2321011111";

            Map<String, Object> places = new HashMap<>();
            places.put("PlaceID", placeid);
            places.put("Name", name);
            places.put("Address", address);
            places.put("Latitude", latitude);
            places.put("Longitude", longitude);
            places.put("Type", types);
            places.put("Phone", phone);

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("places_from_api").add(places).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    // ==========================  CHECKS FOR THE CITY  ======================================

    private boolean addressContainsSerres(String address) {
        return address.contains("Σέρρες") || address.contains("Serres") && address.contains("Greece");
    }


    // ==========================  FETCH DATA FROM THE DATABASE  =============================


    private void fetchDataFromFirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference placesCollection = db.collection("places_from_api");

        Task<QuerySnapshot> queryTask = placesCollection.get();
        queryTask.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    // Τα αποτελέσματα του query είναι εδώ
                    QuerySnapshot querySnapshot = task.getResult();

                    int count = 1;
                    for (QueryDocumentSnapshot document : querySnapshot) {
                        String documentId = document.getId();

                        String placeid = document.getString("PlaceID");
                        String name = document.getString("Name");
                        String address = document.getString("Address");
                        List<String> types = (List<String>) document.get("Type");
                        String phone = document.getString("Phone");
                        double latitude = document.getDouble("Latitude");
                        double longitude = document.getDouble("Longitude");
                        int entry = count;

//                        List<String> placesNames = new ArrayList<>();
//                        List<Double> placesLats = new ArrayList<>();
//                        List<Double> placesLong = new ArrayList<>();
//                        placesNames.add(name);
//                        placesLats.add(latitude);
//                        placesLong.add(longitude);
                        LatLng loc = new LatLng(latitude, longitude);
                        pinFetchedLocations(loc, name);


                        System.out.println("\nEntry: " + count);
                        System.out.println("Name: " + name);
                        System.out.println("Lat: " + latitude);
                        count = count + 1;

                        // Dhmioyrgei ena neo HashMap gia kathe eggrafo
                        HashMap<String, Object> placeData = new HashMap<>();
                        placeData.put("PlaceID", placeid);
                        placeData.put("Name", name);
                        placeData.put("Address", address);
                        placeData.put("Latitude", latitude);
                        placeData.put("Longitude", longitude);
                        placeData.put("Type", types);
                        placeData.put("Phone", phone);
                        placeData.put("Entry", entry);

                        // Apothikeyei ta dedomena me kleidi to documentID
                        dataMap.put(documentId, placeData);

                    }
                } else {
                    System.out.println("Something gone wrong with data download");
                }
            }
        });
    }


    // ==========================  GET LOCATION  ============================================

      private void getLocation() {
        if (checkPermissions()) {

            if (isLocationEnabled()) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location == null) {
                            requestNewLocationData();
                        } else {
                            currentloc = new LatLng(location.getLatitude(),location.getLongitude());
                            System.out.println(currentloc);
                        }
                    }
                });
            }else{
                    Log.i(TAG, "Turn on location");
                }}
        else{
                    requestPermission();
                }

        }


    // ==========================  REQUEST PERMISSION  ======================================

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
    }

    // ==========================  CHECK PERMISSIONS  =======================================

    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    // ==========================  IS LOCATION ENABLED  =====================================

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    // =========================  REQUEST NEW LOCATION DATA  ================================

     private void requestNewLocationData() {

        // Initializing LocationRequest
        // object with appropriate methods
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        // setting LocationRequest
        // on FusedLocationClient
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    // ===========================  LOCATION CALL BACK  =====================================

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            currentloc = new LatLng(mLastLocation.getLatitude(),mLastLocation.getLongitude());
           // lat = mLastLocation.getLatitude();
            //longi = (LatLng) mLastLocation.getLongitude();
        }
    };
}