package com.sparta.as.antsparticans.exceptions;

import java.time.LocalDate;

public class EmployeeNotFoundException extends Exception {

    public EmployeeNotFoundException(String name) {
        super("Employee with last name " + name + " not found");
    }

    public EmployeeNotFoundException(Integer id) {
        super("Employee with id: " + id + " not found");
    }

    public EmployeeNotFoundException(String department, LocalDate date) {
        super("No employees worked in " + department + " on " + date.toString());
    }
}
