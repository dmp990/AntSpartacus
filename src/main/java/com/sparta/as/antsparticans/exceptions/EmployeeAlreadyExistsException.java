package com.sparta.as.antsparticans.exceptions;

public class EmployeeAlreadyExistsException extends Exception {
    public EmployeeAlreadyExistsException() {
        super("Cannot Create. Employee Already Exists!");
    }
}
