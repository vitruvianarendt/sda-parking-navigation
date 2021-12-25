
package com.example.demo.Service;

import com.example.demo.Dao.InterfaceOfDatabase;

import com.example.demo.Model.Coordinates;
import com.example.demo.Model.Parking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class ParkingService {
    private final InterfaceOfDatabase parkingDAO; //od service povikuva eve Database ili DAO

    @Autowired
    public ParkingService(@Qualifier("Database") InterfaceOfDatabase parkingDAO) {
        this.parkingDAO = parkingDAO;
    }

    public List<Parking> getAllParkings()
    {
        return parkingDAO.selectAllParkings();
    }
    public void Decrement(String id)
    {
         parkingDAO.Decrement(id);
    }
    public void Increment(String id)
    {
        parkingDAO.Increment(id);
    }

    public List<Parking> FindNearestParkings(Coordinates cord)
    {
        return parkingDAO.FindNearestParkings(cord);
    }
    public Parking ShortestPathParking(Coordinates cord)
    {
       return parkingDAO.ShortestPathParking(cord);
    }

    public Parking FindAParking(String id) {
        return parkingDAO.FindAParking(id);
    }
}
