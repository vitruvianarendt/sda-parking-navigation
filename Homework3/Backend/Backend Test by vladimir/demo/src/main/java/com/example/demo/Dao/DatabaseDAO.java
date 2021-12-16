package com.example.demo.Dao;

import com.example.demo.Model.Parking;
import org.springframework.stereotype.Repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

@Repository("Database")
public class DatabaseDAO implements InterfaceOfDatabase {
     private final List<Parking> DB = new ArrayList<>();

    @Override
    public List<Parking> selectAllParkings() {
      //  List<Parking> parkingBase = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new FileInputStream("src/main/resources/static/finalParkings.csv"));
            int i = 0;
            sc.nextLine();
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(",");
                UUID id = UUID.randomUUID(); //zimame id's
                String name = line[3];
                double latitude = Double.parseDouble(line[5]);
                double longitude = Double.parseDouble(line[6]);
                int spaces = Integer.parseInt(line[7]);
                int price = Integer.parseInt(line[8]);
                DB.add(new Parking(id, name, longitude, latitude, price, spaces));
              //  i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return DB;
    }


    @Override
   public void Decrement(UUID id)
     {
         int i=0;// sekogash od i=1 da pocne deka prvata linija e samo kolona iminjata
         for(Parking parkingBase : DB)
         {
             if(parkingBase.getId().equals(id))
             {
                 DB.get(i).setSpaces(parkingBase.getSpaces()-1); //namaluvame mesto za 1
                 //menuvame bazata samo
                 return;
             }
             i++;
         }
     }
}
