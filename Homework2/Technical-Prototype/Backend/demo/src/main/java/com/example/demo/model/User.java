package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class User
{
    private final UUID id;
    private final String username;
    private final String email;
    private String password;

    public User(@JsonProperty("id") UUID id,
                @JsonProperty("username") String username,
                @JsonProperty("email") String email,
                @JsonProperty("password") String password)
    {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UUID getId()
    {
        return id;
    }

    public String getUsername()
    {
        return username;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }
}
