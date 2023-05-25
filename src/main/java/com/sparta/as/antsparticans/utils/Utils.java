package com.sparta.as.antsparticans.utils;

import com.sparta.as.antsparticans.model.dtos.DeptEmpDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static ArrayList<Integer> extractEmployeeIdsFromListOfDeptEmpDTOs(List<DeptEmpDTO> listOfDeptEmpDTOs) {
        ArrayList<Integer> listOfEmpIds = new ArrayList<>();
        for (var eachDeptEmpDTO: listOfDeptEmpDTOs) {
            listOfEmpIds.add(eachDeptEmpDTO.getId().getEmpNo());
        }
        return listOfEmpIds;
    }

    public static boolean isDateWithin(LocalDate dateToCheck, LocalDate beginDate, LocalDate endDate) {
        return (beginDate.isBefore(dateToCheck) && endDate.isAfter(dateToCheck)) || (dateToCheck.isEqual(beginDate) || dateToCheck.isEqual(endDate));
    }

    public static boolean isDateRangeWithin(LocalDate startDate, LocalDate endDate, LocalDate fromDate, LocalDate toDate) {
        return (startDate.isAfter(fromDate) && endDate.isBefore(toDate)) || (startDate.isEqual(fromDate) && endDate.isEqual(endDate));
    }
}
