package com.project.BackendParkingNavigation.login;

import com.project.BackendParkingNavigation.appuser.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/login")
@AllArgsConstructor
public class LoginController
{
    private final LoginService loginService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/{email}")
    public AppUser logIn(@PathVariable String email, @RequestBody LoginRequest loginRequest)
    {
        AppUser appUser = new AppUser();
        try
        {
            appUser = loginService.logInUser(loginRequest);
        }
        catch(IncorrectPasswordException e)
        {
            System.out.println(e.printMessage());
        }
        return appUser;
    }
}
