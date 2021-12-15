package com.example.backend1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class User
{
    private final UUID id;
    private final String username;
    private final String email;
    private String password;
    private String[] savedParkings;

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

    public String[] getSavedParkings()
    {
        return savedParkings;
    }

    public void setSavedParkings(String[] savedParkings)
    {
        this.savedParkings = savedParkings;
    }

    public String parkingsToString()
    {
        String S = "";
        for(int i = 0; i < savedParkings.length-1; i++)
        {
            S += savedParkings[i] + ", ";
        }
        S += savedParkings[savedParkings.length-1];
        return S;
    }

    @Override
    public String toString()
    {
        return String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"", id, username, email, password, parkingsToString());
    }
}
