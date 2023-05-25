package com.sparta.as.antsparticans.model.repositories;

import com.sparta.as.antsparticans.model.dtos.SalaryDTO;
import com.sparta.as.antsparticans.model.dtos.SalaryDTOId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryDTORepository extends JpaRepository<SalaryDTO, SalaryDTOId> {
}