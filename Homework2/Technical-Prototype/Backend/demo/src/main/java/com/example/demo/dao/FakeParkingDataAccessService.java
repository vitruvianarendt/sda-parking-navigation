package com.example.demo.dao;

import com.example.demo.model.Parking;
import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("fakeDaoParking")
public class FakeParkingDataAccessService implements ParkingDao
{
    private static List<Parking> parkings = new ArrayList<>();

    @Override
    public int insertParking(Parking parking)
    {
       // parkings.add(new Parking(parking.getName()));
        return 1;
    }

    @Override
    public List<Parking> selectAllParkings()
    {
        if(parkings.isEmpty()) {
            parkings.add(new Parking("Parking Zebra"));
            parkings.add(new Parking("Parking Vero Center"));
            parkings.add(new Parking("Parking Post of Macedonia"));
            parkings.add(new Parking("Clinical Center Parking"));
            parkings.add(new Parking("Cyril and Methodius Parking"));
        }
        return parkings;
    }
}
