package com.sparta.as.antsparticans.controller;

import com.sparta.as.antsparticans.exceptions.EmployeeNotFoundException;
import com.sparta.as.antsparticans.model.dtos.EmployeeDTO;
import com.sparta.as.antsparticans.model.repositories.EmployeeDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeesController {

    EmployeeDTORepository employeeDTORepository;

    @Autowired
    public EmployeesController(EmployeeDTORepository employeeDTORepository) {
        this.employeeDTORepository = employeeDTORepository;
    }

    @GetMapping("/employees/{id}")
    public EmployeeDTO getEmployeeByID(@PathVariable Integer id) throws EmployeeNotFoundException {
        return employeeDTORepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @GetMapping("/employees")
    public List<EmployeeDTO> getEmployeeByLastName(@RequestParam(name = "last_name")String name) throws EmployeeNotFoundException {
        List<EmployeeDTO> employeesByLastName = employeeDTORepository.findEmployeeDTOByLastName(name).get();
        if(employeesByLastName.size() == 0) throw new EmployeeNotFoundException(name);
        return employeesByLastName;
    }

}
