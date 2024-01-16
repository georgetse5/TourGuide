package com.example.tourguide;

import static com.google.common.base.Verify.verify;
import static org.junit.Assert.*;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ActivityOneTest {

    @Test
    public void onCreate() {
    }

    @Test
    public void onMapReady() {
    }

    @Test
    public void onDestroy() {
    }

    @Test
    public void onRequestPermissionsResult() {
    }

    @Test
    public void pinSelectedMarker() {
        LatLng expectedLoc = new LatLng(41.074712, 23.553938);
        LatLng actualLoc = new LatLng(41.074712, 23.553938);
        String placeName = "testPlace";
        String expectedPlaceName = "testPlace";

        MarkerOptions markerOptions10 = new MarkerOptions().position(actualLoc).title(placeName);
        LatLng markerLatLng = markerOptions10.getPosition();

        assertEquals(expectedLoc, markerLatLng);
        assertEquals(expectedPlaceName, placeName);

    }

    @Test
    public void pinSelectedMarkerPlaceName() {
        LatLng expectedLoc = new LatLng(41.074712, 23.553938);
        LatLng actualLoc = new LatLng(41.074712, 23.553938);
        String placeName = "testPlace";
        String expectedPlaceName = "testPlce";

        MarkerOptions markerOptions10 = new MarkerOptions().position(actualLoc).title(placeName);
        LatLng markerLatLng = markerOptions10.getPosition();

//        assertEquals(expectedLoc, markerLatLng);
        assertEquals(expectedPlaceName, placeName);

    }

    @Test
    public void savePlaceToDatabase() {

    }

    @Test
    public void checksForDoublesOnDatabase() {

    }

}