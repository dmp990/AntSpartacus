package com.sparta.as.antsparticans.exceptions;

public class EmployeeViolatesConstraintException extends Exception {

    public EmployeeViolatesConstraintException(Exception e) {
        super("Constraint(s) Violated!\n" + e.getMessage());

    }
}
