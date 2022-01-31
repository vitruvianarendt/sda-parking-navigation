package com.project.BackendParkingNavigation.ParkingFunkcii.ParkingFunctions.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Coordinates {
    private final double Latitude;
    private final double Longitude;

    public Coordinates(
          @JsonProperty("latitude") double latitude,
          @JsonProperty("longitude")  double longitude) {
        Latitude = latitude;
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

}
