package com.sparta.as.antsparticans.controller;

import com.sparta.as.antsparticans.exceptions.EmployeeAlreadyExistsException;
import com.sparta.as.antsparticans.exceptions.EmployeeViolatesConstraintException;
import com.sparta.as.antsparticans.exceptions.EmployeeNotFoundException;
import com.sparta.as.antsparticans.logging.FileHandlerConfig;
import com.sparta.as.antsparticans.model.dtos.EmployeeDTO;
import com.sparta.as.antsparticans.model.repositories.EmployeeDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class EmployeesController {

    private EmployeeDTORepository employeeDTORepository;

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
    public EmployeeDTO replaceEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Integer id) throws EmployeeNotFoundException, EmployeeViolatesConstraintException {

        employeesControllerLogger.log(Level.INFO, "Received: " + employeeDTO + " for " + id);

        Optional<EmployeeDTO> optionalEmployeeDTO = employeeDTORepository.findById(id);
        if (optionalEmployeeDTO.isPresent()) {
            EmployeeDTO oldEmployeeDTO = optionalEmployeeDTO.get();

            // Update only the fields which were passed
            if (employeeDTO.getId() != null)
                oldEmployeeDTO.setId(employeeDTO.getId());
            if (employeeDTO.getFirstName() != null)
                oldEmployeeDTO.setFirstName(employeeDTO.getFirstName());
            if (employeeDTO.getLastName() != null)
                oldEmployeeDTO.setLastName(employeeDTO.getLastName());
            if (employeeDTO.getGender() != null)
                oldEmployeeDTO.setGender(employeeDTO.getGender());
            if (employeeDTO.getBirthDate() != null)
                oldEmployeeDTO.setBirthDate(employeeDTO.getBirthDate());
            if (employeeDTO.getHireDate() != null)
                oldEmployeeDTO.setHireDate(employeeDTO.getHireDate());

            employeesControllerLogger.log(Level.INFO, "Attempting to save: " + oldEmployeeDTO + " to the database");
            EmployeeDTO toBeReturned = null;
            try {
                toBeReturned = employeeDTORepository.save(oldEmployeeDTO);
            } catch (Exception e) {
                employeesControllerLogger.log(Level.INFO, "Constaint Violation Exception Thrown");
                throw new EmployeeViolatesConstraintException(e);
            }
            employeesControllerLogger.log(Level.INFO, "Save successful. About to return: " + toBeReturned + " to the view");
            return toBeReturned;
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }

    private boolean canNewEmployeeBeCreated(EmployeeDTO e) {
        return e.getId() != null && e.getGender() != null && e.getFirstName() != null && e.getLastName() != null && e.getBirthDate() != null && e.getHireDate() != null && employeeDTORepository.findById(e.getId()).isEmpty();
    }

    @PutMapping("/employees")
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) throws EmployeeViolatesConstraintException, EmployeeAlreadyExistsException {

        employeesControllerLogger.log(Level.INFO, "Received: " + employeeDTO);

        if (canNewEmployeeBeCreated(employeeDTO)) {
            employeesControllerLogger.log(Level.INFO, "Can be creted");
            EmployeeDTO toBeReturned = null;

            employeesControllerLogger.log(Level.INFO, "Attempting to create: " + employeeDTO);
            try {
                toBeReturned = employeeDTORepository.save(employeeDTO);
            } catch (Exception e) {
                employeesControllerLogger.log(Level.INFO, "Constraint Violation Exception Thrown");
                throw new EmployeeViolatesConstraintException(e);
            }
            employeesControllerLogger.log(Level.INFO, "Save successful. About to return: " + toBeReturned + " to the view");
            return toBeReturned;

        } else {
            throw new EmployeeAlreadyExistsException();
        }
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployeeById(@PathVariable Integer id) throws EmployeeNotFoundException {


        employeesControllerLogger.log(Level.INFO, "Received id: " + id + " for deletion...");

        Optional<EmployeeDTO> optionalEmployeeDTO = employeeDTORepository.findById(id);
        if (optionalEmployeeDTO.isPresent()) {
            employeesControllerLogger.log(Level.INFO, "Attempting to delete employee with id: " + id + " from the database");
            boolean successfull = false;
            try {
               employeeDTORepository.deleteById(id);
               successfull = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return successfull ? "successfully deleted" : "deletion unsuccessfull";
        } else throw new EmployeeNotFoundException(id);
    }
}
