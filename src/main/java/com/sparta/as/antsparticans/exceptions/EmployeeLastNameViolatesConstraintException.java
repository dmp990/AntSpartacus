package com.sparta.as.antsparticans.exceptions;

public class EmployeeLastNameViolatesConstraintException extends Exception {

    public EmployeeLastNameViolatesConstraintException(String name) {
        super("Last name size must be between 0 and 16");
    }
}
