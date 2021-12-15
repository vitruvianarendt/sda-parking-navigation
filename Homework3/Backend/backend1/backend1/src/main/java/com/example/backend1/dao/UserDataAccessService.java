package com.example.backend1.dao;

import com.example.backend1.model.User;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Repository("dao")
public class UserDataAccessService implements UserDao
{
    @Override
    public int insertUser(UUID id, User user) throws IOException
    {
        User U = new User(id, user.getUsername(), user.getEmail(), user.getPassword());
        PrintWriter printer = new PrintWriter(new FileOutputStream("users.csv", true));
        printer.println(U.toString());
        printer.close();
        return 1;
    }

    @Override
    public List<User> selectAllUsers() throws FileNotFoundException
    {
        List<User> usersBase = new LinkedList<>();
        Scanner scanner = new Scanner(new FileInputStream("users.csv"));
        while(scanner.hasNextLine())
        {
            String[] line = scanner.nextLine().split("\",\"");
            String[] tempParkings = line[4].split(",");
            User U = new User(UUID.fromString(line[0]), line[1], line[2], line[3]);
            U.setSavedParkings(tempParkings);
            usersBase.add(U);
        }
        scanner.close();
        return usersBase;
    }

    @Override
    public int addSavedParking(UUID id, String parking) throws IOException
    {
        Scanner scanner = new Scanner(new FileInputStream("users.csv"));
        Path path = Paths.get("users.csv");
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        int i = 0;
        while(scanner.hasNextLine())
        {
            String[] line = scanner.nextLine().split("\",\"");
            if(line[0].equals(id.toString()))
            {
                String temp = line[4].substring(0, line[4].length()-1);
                temp += "," + parking;
                lines.set(i, String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"", line[0], line[1], line[2], line[3], temp));
                Files.write(path, lines, StandardCharsets.UTF_8);
                break;
            }
            i++;
        }
        scanner.close();
        return 1;
    }
}
