package com.sparta.as.antsparticans.model.repositories;

import com.sparta.as.antsparticans.model.dtos.DepartmentDTO;
import com.sparta.as.antsparticans.model.dtos.DeptEmpDTO;
import com.sparta.as.antsparticans.model.dtos.DeptEmpDTOId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DeptEmpDTORepository extends JpaRepository<DeptEmpDTO, DeptEmpDTOId> {

    List<DeptEmpDTO> findByDeptNo(DepartmentDTO dept);
}