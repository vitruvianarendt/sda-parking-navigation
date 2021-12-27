package com.project.BackendParkingNavigation.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/registration")
@AllArgsConstructor
public class RegistrationController
{
    private final RegistrationService registrationService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public String register(@RequestBody RegistrationRequest request)
    {
        return registrationService.register(request);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token)
    {
        return registrationService.confirmToken(token);
    }
}
