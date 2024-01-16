package com.example.tourguide;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public interface OnDirectionsResultListener {
    void onDirectionsResult(List<LatLng> polylinePoints);
    void onDirectionsFailure();
}
