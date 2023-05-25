package com.sparta.as.antsparticans.model.repositories;

import com.sparta.as.antsparticans.model.dtos.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDTORepository extends JpaRepository<EmployeeDTO, Integer> {
}