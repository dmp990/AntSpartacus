package com.sparta.as.antsparticans.model.repositories;

import com.sparta.as.antsparticans.model.dtos.DepartmentDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentDTORepository extends JpaRepository<DepartmentDTO, String> {
}