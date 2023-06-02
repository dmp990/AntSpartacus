package com.sparta.as.antsparticans.exceptions;

import java.util.Arrays;

public class DateConversionException extends Exception {

    public DateConversionException(Exception e) {
        super("Wrong Date format! Should be YYYY-MM-DD\n" + e.getMessage() + "\n");
    }
}
