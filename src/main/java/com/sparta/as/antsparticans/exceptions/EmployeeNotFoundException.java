package com.sparta.as.antsparticans.exceptions;

public class EmployeeNotFoundException extends Exception{

    public EmployeeNotFoundException(String name) {
        super("Employee with last name " + name + " not found");
    }

    public EmployeeNotFoundException(Integer id) {
        super("Employee with id: " + id + " not found");
    }

}
