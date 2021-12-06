package com.example.demo.api;

import com.example.demo.model.Parking;
import com.example.demo.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/parking")
@RestController
public class ParkingController
{
    private final ParkingService parkingService;

    @Autowired
    public ParkingController(ParkingService parkingService)
    {
        this.parkingService = parkingService;
    }

    @PostMapping
    public void addParking(@RequestBody Parking parking)
    {
        parkingService.addParking(parking);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List<Parking> getParkings()
    {
        return parkingService.getAllParkings();
    }
}
