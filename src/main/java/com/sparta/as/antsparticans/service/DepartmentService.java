package com.sparta.as.antsparticans.service;

import com.sparta.as.antsparticans.model.dtos.DepartmentDTO;
import com.sparta.as.antsparticans.model.dtos.DeptEmpDTO;
import com.sparta.as.antsparticans.model.repositories.DepartmentDTORepository;
import com.sparta.as.antsparticans.model.repositories.DeptEmpDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Service
public class DepartmentService {
    public DepartmentDTORepository departmentDTORepository;
    public DeptEmpDTORepository deptEmpDTORepository;

    @Autowired
    public DepartmentService(DepartmentDTORepository departmentDTORepository, DeptEmpDTORepository deptEmpDTORepository) {
        this.departmentDTORepository = departmentDTORepository;
        this.deptEmpDTORepository = deptEmpDTORepository;
    }

    public HashMap<String, Integer> sizeOfEachDepartmentOverGivenPeriod(LocalDate startYear, LocalDate endYear) {
        List<DepartmentDTO> listOfAllDepartments = departmentDTORepository.getAllDepartments();

        DeptEmpService deptEmpService = new DeptEmpService(departmentDTORepository, deptEmpDTORepository);

        return createHashMapWithSummaryOfDepartmentSize(listOfAllDepartments, deptEmpService, startYear, endYear);
    }

    private static HashMap<String, Integer> createHashMapWithSummaryOfDepartmentSize(List<DepartmentDTO> listOfAllDepartments, DeptEmpService deptEmpService, LocalDate startYear, LocalDate endYear) {
        HashMap<String, Integer> summaryOfDepartmentSizes = new HashMap<>();

        for (var each : listOfAllDepartments) {
            List<DeptEmpDTO> deptEmpDTOS = deptEmpService.getListOfDeptEmpsWorkingInADepartmentOnAGivenDateRange(each.getDeptName(), startYear, endYear);
            summaryOfDepartmentSizes.put(each.getDeptName(), deptEmpDTOS.size());
        }

        return summaryOfDepartmentSizes;

    }
}

/*
Provide a summary of the size of each department (number of staff) over a given period (start year to end year)
 */
