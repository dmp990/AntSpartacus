package com.sparta.as.antsparticans.model.repositories;

import com.sparta.as.antsparticans.model.dtos.SalaryDTO;
import com.sparta.as.antsparticans.model.dtos.SalaryDTOId;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SalaryDTORepository extends JpaRepository<SalaryDTO, SalaryDTOId> {

    @Query(value = "SELECT * FROM employees.salaries", nativeQuery = true)
    List<SalaryDTO> getAllSalaries();

    // 2000 - 2001

    @Query(value = "SELECT MIN(salary), MAX(salary) FROM employees.salaries JOIN employees.titles ON salaries.emp_no = titles.emp_no WHERE titles.title =:title AND salaries.from_date >=:startDate AND salaries.to_date <=:endDate", nativeQuery = true)
    List<SalaryDTO> getAllSalariesWithinADateRangeForAGivenTitle(String title, LocalDate startDate, LocalDate endDate);



}