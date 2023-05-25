package com.sparta.as.antsparticans.service;

import com.sparta.as.antsparticans.model.dtos.EmployeeDTO;
import com.sparta.as.antsparticans.model.repositories.EmployeeDTORepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class EmployeeServiceTests {
    @Autowired
    EmployeeDTORepository employeeDTORepository;

    @Autowired
    EmployeeService employeeService;

    @Test
    @DisplayName("Check find employeeDTO by last name returns 186 employees with the last name of Facello")
    void checkFindEmployeeDTOByLastName() {
        List<EmployeeDTO> employeeDTOS = employeeService.findEmployeesByLastName("Facello");
        Assertions.assertEquals(186, employeeDTOS.size());

    }

    @Test
    @DisplayName("Check find employeeDTO by last name returns no employees with the last name of Ali")
    void checkFindEmployeeDTOByLastNameWhereNoEmployeeExists() {
        List<EmployeeDTO> employeeDTOS = employeeService.findEmployeesByLastName("Ali");
        Assertions.assertTrue(employeeDTOS.size() == 0);

    }

    @Test
    @DisplayName("Check if findEmployeesWhoWorkedInDepartment")
    void checkFindEmployeesWhoWorkedInDepartment() {
        System.out.println(employeeService.findEmployeesWhoWorkedInDepartmentOnAGivenDate("Marketing", LocalDate.parse("2005-10-10")).size() + " <<<");
    }
}
