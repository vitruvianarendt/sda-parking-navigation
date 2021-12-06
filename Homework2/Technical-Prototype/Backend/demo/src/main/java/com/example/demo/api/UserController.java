package com.example.demo.api;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void addUser(@RequestBody User user)
    {
        userService.addUser(user);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }
}
