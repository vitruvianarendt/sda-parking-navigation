package com.example.demo.service;

import com.example.demo.dao.ParkingDao;
import com.example.demo.model.Parking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingService
{
    private final ParkingDao parkingDao;

    @Autowired
    public ParkingService(@Qualifier("fakeDaoParking")ParkingDao parkingDao)
    {
        this.parkingDao = parkingDao;
    }

    public int addParking(Parking parking)
    {
        return parkingDao.insertParking(new Parking(parking.getName()));
    }

    public List<Parking> getAllParkings()
    {
        return parkingDao.selectAllParkings();
    }
}
