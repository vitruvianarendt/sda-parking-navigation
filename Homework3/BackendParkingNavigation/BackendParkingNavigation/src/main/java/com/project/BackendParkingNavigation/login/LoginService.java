package com.project.BackendParkingNavigation.login;

import com.project.BackendParkingNavigation.appuser.AppUser;
import com.project.BackendParkingNavigation.appuser.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class LoginService
{
    private BCryptPasswordEncoder encoder;
    private UserRepository userRepository;

    public AppUser logInUser(LoginRequest loginRequest) throws IncorrectPasswordException
    {
        AppUser appUser = this.userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(
                () -> new UsernameNotFoundException("User not found"));
        if(!encoder.matches(loginRequest.getPassword(), appUser.getPassword()))
            throw new IncorrectPasswordException("Incorrect password");
        return appUser;
    }
}
