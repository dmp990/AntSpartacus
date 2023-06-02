package com.sparta.as.antsparticans.exceptions;

import com.sparta.as.antsparticans.controller.EmployeesController;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DepartmentNotFoundException extends Exception {

    private static final Logger DepartmentNotFoundExceptionLogger = Logger.getLogger(EmployeesController.class.getName());

    public DepartmentNotFoundException(String dept_no) {
        super("Invalid department number: " + dept_no);
        DepartmentNotFoundExceptionLogger.log(Level.WARNING, "DepartmentNotFoundException for dept no: " + dept_no);
    }

    public DepartmentNotFoundException(String department_name, boolean itsaname) {
        super("No department with name: " + department_name);
    }
}