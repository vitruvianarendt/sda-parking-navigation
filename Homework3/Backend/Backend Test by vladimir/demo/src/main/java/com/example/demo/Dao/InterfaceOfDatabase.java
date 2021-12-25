package com.example.demo.Dao;

import com.example.demo.Model.Coordinates;
import com.example.demo.Model.Parking;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
