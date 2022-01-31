package com.project.BackendParkingNavigation.ParkingFunkcii.ParkingFunctions.API;

//import com.example.demo.Model.Coordinates;
//import com.example.demo.Model.Parking;
//import com.example.demo.Service.ParkingService;
import com.project.BackendParkingNavigation.ParkingFunkcii.ParkingFunctions.Model.Coordinates;
import com.project.BackendParkingNavigation.ParkingFunkcii.ParkingFunctions.Model.Parking;
import com.project.BackendParkingNavigation.ParkingFunkcii.ParkingFunctions.Service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/parking")
public class APIParkingController
{
    private final ParkingService parkingService;

    @Autowired
    public APIParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping
    public List<Parking> getAllParkings()
    {
        return parkingService.getAllParkings();
    }


//    @PutMapping(path = "{id}")
//    public void updatePersonByID(@PathVariable("id") UUID id,@RequestBody Person personToupdate)
//    {
//        personService.updatePersonByID(id,personToupdate);
//    }


    //ova e update na bazata ;)
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(path = "/decrement/{id}")
    public void Decrement(@PathVariable("id") String id)
    {
         parkingService.Decrement(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(path = "/increment/{id}")
    public void Increment(@PathVariable("id") String id)
    {
        parkingService.Increment(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/NearParkings")
    public List<Parking> nearestParkings(@RequestBody Coordinates cord)
    {
        return parkingService.FindNearestParkings(cord);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/Nearest")
    public Parking ShortestPathParking(@RequestBody Coordinates cord)
    {
        return parkingService.ShortestPathParking(cord);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/Specific/{id}")
    public Parking FindAParking(@PathVariable String id) {
        return parkingService.FindAParking(id);
    }
}
