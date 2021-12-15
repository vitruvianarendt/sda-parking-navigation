package com.example.backend1.api;

import com.example.backend1.model.User;
import com.example.backend1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/user")
@RestController
public class UserController
{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping
    public void addUser(@RequestBody User user) throws IOException
    {
        userService.addUser(user);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

    //treba da ima dependency nekakov za promenlivive
    @PostMapping
    public int addParking(UUID id, String parking) throws FileNotFoundException
    {
        return userService.addParking(id, parking);
    }
}
