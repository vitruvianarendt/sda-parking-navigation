package com.project.BackendParkingNavigation.appuser;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/password")
@AllArgsConstructor
public class UserPasswordController
{
    private final UserService userService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/edit/{username}")
    public Boolean editPassword(@PathVariable String username, @RequestBody String password)
    {
        return userService.changePassword(username, password);
    }
}
