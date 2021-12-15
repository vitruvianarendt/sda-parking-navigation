package com.example.backend1.dao;

import com.example.backend1.model.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface UserDao
{
    int insertUser(UUID id, User user) throws IOException;

    default int insertUser(User user) throws IOException
    {
        UUID id = UUID.randomUUID();
        return insertUser(id, user);
    }

    List<User> selectAllUsers() throws FileNotFoundException;

    int addSavedParking(UUID id, String parking) throws IOException;
}
