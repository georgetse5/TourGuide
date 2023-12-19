package com.example.tourguide;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlacesTest {

    Places placeGet = new Places("Μουσείο Φυσικής Ιστορίας", 41.0998109, 23.5696117);
    Places placeSet = new Places("Λαογραφικό Μουσείο Σαρακατσάνων", 41.0940141, 23.554875);

    @Test
    public void getName() {
        placeGet.setName("Μουσείο Φυσικής Ιστορίας");

        assertEquals("Μουσείο Φυσικής Ιστορίας", placeGet.getName());
    }

    @Test
    public void setName() {

        assertEquals(placeSet.getName(), "Λαογραφικό Μουσείο Σαρακατσάνων");
//        fail();
    }

    @Test
    public void getLatitude() {

        assertEquals(41.0998109, placeGet.getLatitude(), 1e-7);
    }

    @Test
    public void setLatitude() {

        assertEquals(placeSet.getLatitude(), 41.0940141, 1e-7);
    }

    @Test
    public void getLongitude() {

        assertEquals(23.5696117, placeGet.getLongitude(), 1e-7);
    }

    @Test
    public void setLongitude() {

        assertEquals(placeSet.getLongitude(), 23.554875, 1e-7);
    }

}