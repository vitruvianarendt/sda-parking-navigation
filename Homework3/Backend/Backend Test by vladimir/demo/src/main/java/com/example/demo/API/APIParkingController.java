package com.example.demo.API;

import com.example.demo.Model.Coordinates;
import com.example.demo.Model.Parking;
import com.example.demo.Service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    @PutMapping(path = "/decrement/{id}")
    public void Decrement(@PathVariable("id") String id)
    {
         parkingService.Decrement(id);
    }

    @PutMapping(path = "/increment/{id}")
    public void Increment(@PathVariable("id") String id)
    {
        parkingService.Increment(id);
    }

    @GetMapping(path = "/NearParkings")
    public List<Parking> nearestParkings(@RequestBody Coordinates cord)
    {
        return parkingService.FindNearestParkings(cord);
    }

    @GetMapping(path = "/Nearest")
    public Parking ShortestPathParking(@RequestBody Coordinates cord)
    {
        return parkingService.ShortestPathParking(cord);
    }
    @GetMapping(path = "/Specific/{id}")
    public Parking FindAParking(@PathVariable String id) {
        return parkingService.FindAParking(id);
    }
}
