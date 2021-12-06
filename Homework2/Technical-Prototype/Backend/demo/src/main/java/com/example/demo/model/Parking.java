package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Parking
{
    private final String name;

    public Parking(@JsonProperty("parkingName") String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
