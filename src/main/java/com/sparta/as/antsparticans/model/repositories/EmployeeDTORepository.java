package com.sparta.as.antsparticans.model.repositories;

import com.sparta.as.antsparticans.model.dtos.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface EmployeeDTORepository extends JpaRepository<EmployeeDTO, Integer> {
    /*
    Find employees by last name
     */
    Optional<List<EmployeeDTO>> findEmployeeDTOByLastName(String lastName);

    EmployeeDTO findEmployeeDTOById(int id);

    @Query(value = "SELECT * FROM employees.employees LIMIT 1000", nativeQuery = true)
    List<EmployeeDTO> getAllEmployees();

}