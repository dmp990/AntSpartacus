package com.sparta.as.antsparticans.model.repositories;

import com.sparta.as.antsparticans.model.dtos.DepartmentDTO;
import com.sparta.as.antsparticans.model.dtos.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DepartmentDTORepository extends JpaRepository<DepartmentDTO, String> {
    DepartmentDTO findByDeptName(String deptName);

    @Query(value = "SELECT * FROM employees.departments", nativeQuery = true)
    List<DepartmentDTO> getAllDepartments();
}