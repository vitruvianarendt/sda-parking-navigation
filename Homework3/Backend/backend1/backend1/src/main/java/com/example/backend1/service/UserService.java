package com.example.backend1.service;

import com.example.backend1.dao.UserDao;
import com.example.backend1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class UserService
{
    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("dao") UserDao userDao)
    {
        this.userDao = userDao;
    }

    public int addUser(User user) throws IOException
    {
        return userDao.insertUser(user);
    }

    public List<User> getAllUsers()
    {
        return userDao.selectAllUsers();
    }

    public int addParking(UUID id, String parking) throws FileNotFoundException
    {
        return userDao.addSavedParking(id, parking);
    }
}
