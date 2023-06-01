package com.sparta.as.antsparticans.exceptions;

import com.sparta.as.antsparticans.controller.EmployeesController;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DepartmentNameAlreadyExistsException extends Exception{

    private static final Logger DepartmentNameAlreadyExistsExceptionLogger = Logger.getLogger(EmployeesController.class.getName());
    public DepartmentNameAlreadyExistsException(String dept_name) {
        super("Department name " + dept_name + " already exists.");
        DepartmentNameAlreadyExistsExceptionLogger.log(Level.WARNING, "DepartmentNameAlreadyExists Exception for dept name: " + dept_name);
    }
}
