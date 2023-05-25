package com.sparta.as.antsparticans.utils;

import java.time.LocalDate;

public class DateValidator {

    public static boolean isDateWithin(LocalDate dateToCheck, LocalDate beginDate, LocalDate endDate) {
        return (beginDate.isBefore(dateToCheck) && endDate.isAfter(dateToCheck)) || (dateToCheck.isEqual(beginDate) || dateToCheck.isEqual(endDate));
    }
}
