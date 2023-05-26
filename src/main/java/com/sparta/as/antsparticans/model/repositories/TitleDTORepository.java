package com.sparta.as.antsparticans.model.repositories;

import com.sparta.as.antsparticans.model.dtos.TitleDTO;
import com.sparta.as.antsparticans.model.dtos.TitleDTOId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TitleDTORepository extends JpaRepository<TitleDTO, TitleDTOId> {

    @Query(value = "SELECT * FROM employees.titles WHERE title=:title", nativeQuery = true)
    List<TitleDTO> getAllTitleDTOsByTitle(String title);
}