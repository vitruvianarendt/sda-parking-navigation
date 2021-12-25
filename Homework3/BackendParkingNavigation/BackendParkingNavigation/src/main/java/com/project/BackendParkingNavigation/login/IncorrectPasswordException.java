package com.project.BackendParkingNavigation.login;

public class IncorrectPasswordException extends Exception
{
    public IncorrectPasswordException(String message)
    {
        super(message);
    }

    public String printMessage()
    {
        return super.getMessage();
    }
}
