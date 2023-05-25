package com.sparta.as.antsparticans.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashMap;

@SpringBootTest
public class DepartmentServiceTests {

    @Autowired
    DepartmentService departmentService;

    @Test
    @DisplayName("Check department summary works")
    void checkDepartmentSummaryWorks() {
        HashMap<String, Integer> hashMap =
                departmentService.sizeOfEachDepartmentOverGivenPeriod(LocalDate.parse("2005-01-01"), LocalDate.parse("2006-01-01"));

        for (var each: hashMap.keySet()) {
            System.out.println(each + ": " + hashMap.get(each));
        }
    }
}
