
package com.project.BackendParkingNavigation.ParkingFunkcii.ParkingFunctions.Service;

//import com.example.demo.Dao.InterfaceOfDatabase;
//import com.example.demo.Model.Coordinates;
//import com.example.demo.Model.Parking;
import com.project.BackendParkingNavigation.ParkingFunkcii.ParkingFunctions.Dao.InterfaceOfDatabase;
import com.project.BackendParkingNavigation.ParkingFunkcii.ParkingFunctions.Model.Coordinates;
import com.project.BackendParkingNavigation.ParkingFunkcii.ParkingFunctions.Model.Parking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

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
