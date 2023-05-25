package com.sparta.as.antsparticans.model.repositories;

import com.sparta.as.antsparticans.model.dtos.SalaryDTO;
import com.sparta.as.antsparticans.model.dtos.SalaryDTOId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalaryDTORepository extends JpaRepository<SalaryDTO, SalaryDTOId> {

    @Query(value = "SELECT * FROM employees.salaries", nativeQuery = true)
    List<SalaryDTO> getAllSalaries();

}