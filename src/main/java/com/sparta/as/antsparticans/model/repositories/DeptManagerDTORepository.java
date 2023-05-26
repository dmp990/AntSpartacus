package com.sparta.as.antsparticans.model.repositories;

import com.sparta.as.antsparticans.model.dtos.DeptManagerDTO;
import com.sparta.as.antsparticans.model.dtos.DeptManagerDTOId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptManagerDTORepository extends JpaRepository<DeptManagerDTO, DeptManagerDTOId> {
}