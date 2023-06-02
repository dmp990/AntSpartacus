package com.sparta.as.antsparticans.controller;

import com.sparta.as.antsparticans.exceptions.EmployeeNotFoundException;
import com.sparta.as.antsparticans.logging.FileHandlerConfig;
import com.sparta.as.antsparticans.model.dtos.EmployeeDTO;
import com.sparta.as.antsparticans.model.repositories.EmployeeDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.sparta.as.antsparticans.logging.FileHandlerConfig.getFileHandler;

@RestController
public class EmployeesController {

    EmployeeDTORepository employeeDTORepository;

    private static final Logger employeesControllerLogger = Logger.getLogger(EmployeesController.class.getName());

    static {
        employeesControllerLogger.setUseParentHandlers(false);
        employeesControllerLogger.setLevel(Level.ALL);
        employeesControllerLogger.addHandler(FileHandlerConfig.getFileHandler());
    }

        @Autowired
    public EmployeesController(EmployeeDTORepository employeeDTORepository) {
        this.employeeDTORepository = employeeDTORepository;
        employeesControllerLogger.log(Level.INFO, "Employees controller constructor employeeDTO dependency created");
    }

    @GetMapping("/employees/{id}")
    public EmployeeDTO getEmployeeByID(@PathVariable Integer id) throws EmployeeNotFoundException {
        return employeeDTORepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @GetMapping("/employees")
    public List<EmployeeDTO> getEmployeeByLastName(@RequestParam(name = "last_name") Optional<String> name) throws EmployeeNotFoundException {
        if (!name.isPresent()) {
            return employeeDTORepository.getAllEmployees();
        }
        List<EmployeeDTO> employeesByLastName = employeeDTORepository.findEmployeeDTOByLastName(name.get()).get();
        if (employeesByLastName.isEmpty()) throw new EmployeeNotFoundException(name.get());
        return employeesByLastName;
    }
}
