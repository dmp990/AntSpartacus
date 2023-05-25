package com.sparta.as.antsparticans.model.repositories;

import com.sparta.as.antsparticans.model.dtos.DeptEmpDTO;
import com.sparta.as.antsparticans.model.dtos.DeptEmpDTOId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptEmpDTORepository extends JpaRepository<DeptEmpDTO, DeptEmpDTOId> {
}