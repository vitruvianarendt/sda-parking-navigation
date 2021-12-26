package com.project.BackendParkingNavigation.ParkingFunkcii.ParkingFunctions.Dao;

//import com.example.demo.Model.Coordinates;
//import com.example.demo.Model.Parking;
import com.project.BackendParkingNavigation.ParkingFunkcii.ParkingFunctions.Model.Coordinates;
import com.project.BackendParkingNavigation.ParkingFunkcii.ParkingFunctions.Model.Parking;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Repository("Database")
public class DatabaseDAO implements InterfaceOfDatabase {
     private final List<Parking> DB = new ArrayList<>();

    @Override
    public List<Parking> selectAllParkings() {
      //  List<Parking> parkingBase = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new FileInputStream("src/main/resources/static/finalParkings.csv"));
            sc.nextLine();
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(",");
                String id = line[0];
                String vtoraKolona = line[1];
                String addrCity =line[2];
                String name=line[3];
                String addrStreet=line[4];
                double latitude=Double.parseDouble(line[5]);
                double longitde=Double.parseDouble(line[6]);
                int spaces = Integer.parseInt(line[7]);
                int price = Integer.parseInt(line[8]);
                DB.add(new Parking(id,vtoraKolona,addrCity,name,addrStreet,latitude,longitde,spaces,price));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return DB;
    }


    @Override
   public void Decrement(String id)
     {
         int i=0;
         if(DB.isEmpty())
         {
             selectAllParkings();
         }
         for(Parking parkingBase : DB)
         {
             if(parkingBase.getId().equals(id))
             {
                 DB.get(i).setSpaces(parkingBase.getSpaces()-1); //namaluvame mesto za 1
                 try {
                     File myFoo = new File("src/main/resources/static/finalParkings.csv");
                     FileWriter fooWriter = new FileWriter(myFoo, false); // true to append
                     fooWriter.write("\n");
                     // false to overwrite.
                     for(Parking k : DB)
                     {
                         fooWriter.write(k.toString() + "\n");
                     }
                     fooWriter.flush();
                     fooWriter.close();
                 } catch (FileNotFoundException e) {
                     System.out.println("An error occurred.");
                     e.printStackTrace();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
                 return;
             }
             i++;
         }
     }

    @Override
    public void Increment(String id) {
        int i=0;
        if(DB.isEmpty())
        {
            selectAllParkings();
        }
        for(Parking parkingBase : DB)
        {
            if(parkingBase.getId().equals(id))
            {
                DB.get(i).setSpaces(parkingBase.getSpaces()+1); //zgolemuvae mesto za 1
                try {
                    File myFoo = new File("src/main/resources/static/finalParkings.csv");
                    FileWriter fooWriter = new FileWriter(myFoo, false); // true to append
                    fooWriter.write("\n");
                    // false to overwrite.
                    for(Parking k : DB)
                    {
                        fooWriter.write(k.toString() + "\n");
                    }
                    fooWriter.flush();
                    fooWriter.close();
                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
            i++;
        }
    }

    @Override
    public List<Parking> FindNearestParkings(Coordinates cord) {
        List<Parking> nearest = new ArrayList<>();
        if(DB.isEmpty())
        {
            selectAllParkings();
        }
        for(Parking park : DB)
        {
            double kilometers = distance(park.getLatitude(),cord.getLatitude(),park.getLongitde(),cord.getLongitude());
            if(kilometers<=1)
            {
                int flag=1;
              for(int i=0;i<nearest.size();i++) //proverka dali e so istiot id deka ne e tocna bash bazata
              {
                  if(park.getId().equals(nearest.get(i).getId()))
                  {
                      flag=0;
                  }
              }
              if(flag==1)
                  nearest.add(park);

            }
        }
        return nearest;
    }
    @Override
    public Parking ShortestPathParking(Coordinates cord)
       {
           double min=999999;
       if(DB.isEmpty())
       {
           selectAllParkings();
       }
       Parking najblisku = null;
       for(Parking park : DB)
       {
               double kilometers = distance(park.getLatitude(),cord.getLatitude(),park.getLongitde(),cord.getLongitude()); //gets kilometers
               if(kilometers<min)
               {
                  min=kilometers;//nov kilometeres i nov objekt treba
                  najblisku  = new Parking(
                           park.getId(),
                           park.getVtoraKolona(),
                           park.getAddrCity(),
                           park.getName(),
                           park.getAddrStreet(),
                           park.getLatitude(),
                           park.getLongitde(),
                           park.getSpaces(),
                           park.getPrice());
               }
           }
       return najblisku;
       }

    @Override
    public Parking FindAParking(String id) {
        if(DB.isEmpty())
        {
            selectAllParkings();
        }
        for(Parking park : DB)
        {
            if(park.getId().equals(id))
            {
                return park;
            }
        }
        return null;
    }

//function for calculating distance between 2 coordinates on the map
    public double distance(double lat1,
                                  double lat2, double lon1,
                                  double lon2)
    {

        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);
        double c = 2 * Math.asin(Math.sqrt(a));
        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;
        return(c * r);
    }
}
