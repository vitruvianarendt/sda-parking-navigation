package com.example.demo.dao;

import com.example.demo.model.Parking;

import java.util.List;

public interface ParkingDao
{
    int insertParking(Parking parking);
    List<Parking> selectAllParkings();
}
