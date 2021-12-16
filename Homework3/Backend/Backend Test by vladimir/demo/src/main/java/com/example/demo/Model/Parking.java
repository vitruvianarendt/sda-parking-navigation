package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

//modelot e ova
public class Parking {
    private final UUID id;
    private final String name;
    private final double longitude;
    private final double Latitude;
    private final int price;
    private int spaces;

    public Parking(@JsonProperty("id")UUID id,
                   @JsonProperty("name")String name,
                   @JsonProperty("latitude")  double latitude,
                   @JsonProperty("longitude")  double longitude,
                   @JsonProperty("price")  int price,
                   @JsonProperty("space") int spaces) {
        this.id = id;
        this.name = name;
        this.Latitude = latitude;
        this.longitude = longitude;
        this.price = price;
        this.spaces = spaces;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public int getPrice() {
        return price;
    }

    public int getSpaces() {
        return spaces;
    }

    public void setSpaces(int spaces) {
        this.spaces=spaces;
    }
    @Override
    public String toString() {
        return "Parking{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", longitude=" + Latitude +
                ", Latitude=" + longitude+
                ", price=" + price +
                ", spaces=" + spaces +
                '}';
    }
}
