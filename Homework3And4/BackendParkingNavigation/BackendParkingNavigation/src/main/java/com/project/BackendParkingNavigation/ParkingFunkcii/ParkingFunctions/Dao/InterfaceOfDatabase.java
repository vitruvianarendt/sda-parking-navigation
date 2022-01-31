package com.project.BackendParkingNavigation.ParkingFunkcii.ParkingFunctions.Dao;

//import com.example.demo.Model.Coordinates;
//import com.example.demo.Model.Parking;
import com.project.BackendParkingNavigation.ParkingFunkcii.ParkingFunctions.Model.Coordinates;
import com.project.BackendParkingNavigation.ParkingFunkcii.ParkingFunctions.Model.Parking;

import java.util.List;

public interface InterfaceOfDatabase {
    //interface za da gi pisheme samo metodite
        //za da go vrati na skreen
        List<Parking> selectAllParkings();
        void Decrement(String id);//ova e samo za namaluvanje na mesto toest za koga kje vlezes
        void Increment(String id);
        List<Parking> FindNearestParkings(Coordinates cord);
        Parking ShortestPathParking(Coordinates cord);
        Parking FindAParking (String id);

    }
