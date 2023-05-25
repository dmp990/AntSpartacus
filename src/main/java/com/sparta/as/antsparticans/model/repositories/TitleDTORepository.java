package com.sparta.as.antsparticans.model.repositories;

import com.sparta.as.antsparticans.model.dtos.TitleDTO;
import com.sparta.as.antsparticans.model.dtos.TitleDTOId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleDTORepository extends JpaRepository<TitleDTO, TitleDTOId> {
}