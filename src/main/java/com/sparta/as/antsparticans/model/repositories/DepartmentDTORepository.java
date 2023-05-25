package com.sparta.as.antsparticans.model.repositories;

import com.sparta.as.antsparticans.model.dtos.DepartmentDTO;
import com.sparta.as.antsparticans.model.dtos.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentDTORepository extends JpaRepository<DepartmentDTO, String> {
    DepartmentDTO findByDeptName(String deptName);
}