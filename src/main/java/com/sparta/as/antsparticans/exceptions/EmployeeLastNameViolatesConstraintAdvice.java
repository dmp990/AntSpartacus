package com.sparta.as.antsparticans.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EmployeeLastNameViolatesConstraintAdvice {

    @ResponseBody
    @ExceptionHandler(EmployeeLastNameViolatesConstraintException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String employeeNotFoundHandler(EmployeeLastNameViolatesConstraintException e) {
        return e.getMessage();
    }
}
