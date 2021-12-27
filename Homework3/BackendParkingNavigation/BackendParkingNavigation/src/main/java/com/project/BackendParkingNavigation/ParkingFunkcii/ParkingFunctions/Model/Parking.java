package com.project.BackendParkingNavigation.ParkingFunkcii.ParkingFunctions.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

//modelot e ova
public class Parking {
    private final String id;
    private final  String VtoraKolona;
    private final String addrCity;
    private final String name;
    private final String addrStreet;
    private final double Latitude;
    private final double Longitde;
    private int spaces;
    private final int price;

    public Parking(@JsonProperty("id") String id,
                   @JsonProperty("vtoraKolona")  String vtoraKolona,
                   @JsonProperty("addrCity")   String addrCity,
                   @JsonProperty("name")   String name,
                   @JsonProperty("addrStreet")   String addrStreet,
                   @JsonProperty("latitude") double latitude,
                   @JsonProperty("longitde") double longitde,
                   @JsonProperty("spaces")   int spaces,
                   @JsonProperty("price")    int price) {
        this.id = id;
        VtoraKolona = vtoraKolona;
        this.addrCity = addrCity;
        this.name = name;
        this.addrStreet = addrStreet;
        Latitude = latitude;
        Longitde = longitde;
        this.spaces = spaces;
        this.price = price;
    }

    public String  getId() {
        return id;
    }

    public String getVtoraKolona() {
        return VtoraKolona;
    }

    public String getAddrCity() {
        return addrCity;
    }

    public String getName() {
        return name;
    }

    public String getAddrStreet() {
        return addrStreet;
    }

    public double getLatitude() {
        return Latitude;
    }

    public double getLongitde() {
        return Longitde;
    }

    public int getSpaces() {
        return spaces;
    }

    public int getPrice() {
        return price;
    }

    public void setSpaces(int spaces) {
        this.spaces = spaces;
    }

    //120,386746863,,Паркинг МОБ,,41.9967331,21.436413,50,30
    @Override
    public String toString() {
        return id +","+ VtoraKolona+ "," + addrCity + "," + name + "," +
                addrStreet + "," + Latitude + "," +  Longitde + "," + spaces + "," + price;
    }
}
