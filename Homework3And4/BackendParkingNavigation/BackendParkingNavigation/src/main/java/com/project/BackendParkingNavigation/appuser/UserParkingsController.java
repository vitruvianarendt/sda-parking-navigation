package com.project.BackendParkingNavigation.appuser;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/parking")
@AllArgsConstructor
public class UserParkingsController
{
    private final UserService userService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add/{username}")
    public Boolean addParking(@PathVariable String username, @RequestBody String parking)
    {
        return userService.setParking(username, parking);
    }
}
