package com.sparta.as.antsparticans.controller;

import com.sparta.as.antsparticans.exceptions.EmployeeLastNameViolatesConstraintException;
import com.sparta.as.antsparticans.exceptions.EmployeeNotFoundException;
import com.sparta.as.antsparticans.logging.FileHandlerConfig;
import com.sparta.as.antsparticans.model.dtos.EmployeeDTO;
import com.sparta.as.antsparticans.model.repositories.EmployeeDTORepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /*
    departmentsDAOLogger.log(Level.FINE, "DepartmentsDAO created");
     */
    @Autowired
    public EmployeesController(EmployeeDTORepository employeeDTORepository) {
        this.employeeDTORepository = employeeDTORepository;
        employeesControllerLogger.log(Level.INFO, "Employees controller constructor employeeDTO dependency created");
    }

    @GetMapping("/employees/{id}")
    public EmployeeDTO getEmployeeByID(@PathVariable Integer id) throws EmployeeNotFoundException {
        employeesControllerLogger.log(Level.INFO, "Fetching employee with id: " + id + " from the database...");
        return employeeDTORepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @GetMapping("/employees")
    public List<EmployeeDTO> getEmployeeByLastName(@RequestParam(name = "last_name") Optional<String> name) throws EmployeeNotFoundException {
        employeesControllerLogger.log(Level.INFO, "Fetching employee with lastName: " + name + " from the database...");
        if (!name.isPresent()) {
            return employeeDTORepository.getAllEmployees();
        }
        List<EmployeeDTO> employeesByLastName = employeeDTORepository.findEmployeeDTOByLastName(name.get()).get();
        if (employeesByLastName.isEmpty()) throw new EmployeeNotFoundException(name.get());
        return employeesByLastName;
    }

    @PutMapping("/employees/{id}")
    public EmployeeDTO replaceLastNameByEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Integer id) throws EmployeeNotFoundException, EmployeeLastNameViolatesConstraintException {
        employeesControllerLogger.log(Level.INFO, "Method called");
        employeesControllerLogger.log(Level.INFO, "Received: " + employeeDTO + " for " + id);
        Optional<EmployeeDTO> optionalEmployeeDTO = employeeDTORepository.findById(id);
        if (optionalEmployeeDTO.isPresent()) {
            EmployeeDTO oldEmployeeDTO = optionalEmployeeDTO.get();
            oldEmployeeDTO.setLastName(employeeDTO.getLastName());
            try {
                return employeeDTORepository.save(oldEmployeeDTO);
            } catch (ConstraintViolationException e) {
                throw new EmployeeLastNameViolatesConstraintException(employeeDTO.getLastName());
            }
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }
}
