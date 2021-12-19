package com.project.BackendParkingNavigation.appuser;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/parking")
@AllArgsConstructor
public class UserParkingsController
{
    private final UserService userService;

    @PostMapping("/add/{id}")
    public Boolean addParking(@PathVariable Long id, @RequestBody String parking) {
        return userService.setParking(id, parking);
    }
}
