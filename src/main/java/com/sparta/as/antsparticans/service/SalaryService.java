package com.sparta.as.antsparticans.service;

import com.sparta.as.antsparticans.model.dtos.DeptEmpDTO;
import com.sparta.as.antsparticans.model.dtos.SalaryDTO;
import com.sparta.as.antsparticans.model.repositories.DepartmentDTORepository;
import com.sparta.as.antsparticans.model.repositories.DeptEmpDTORepository;
import com.sparta.as.antsparticans.model.repositories.SalaryDTORepository;
import com.sparta.as.antsparticans.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SalaryService {

    public final DeptEmpDTORepository deptEmpDTORepository;

    private final DepartmentDTORepository departmentDTORepository;

    private final SalaryDTORepository salaryDTORepository;

    @Autowired
    public SalaryService(DeptEmpDTORepository deptEmpDTORepository, DepartmentDTORepository departmentDTORepository, SalaryDTORepository salaryDTORepository) {
        this.deptEmpDTORepository = deptEmpDTORepository;
        this.departmentDTORepository = departmentDTORepository;
        this.salaryDTORepository = salaryDTORepository;
    }

    public double getAverageSalaryForADepartmentOnAGivenDate(String deptName, LocalDate date) {
        DeptEmpService deptEmpService = new DeptEmpService(departmentDTORepository, deptEmpDTORepository);

        List<DeptEmpDTO> listOfDeptEmpsWorkingInADepartmentOnAGivenDate = deptEmpService.getListOfDeptEmpsWorkingInADepartmentOnAGivenDate(deptName, date);

        List<Integer> listOfEmployeeIdsWorkingInADepartmentOnAGivenDate = Utils.extractEmployeeIdsFromListOfDeptEmpDTOs(listOfDeptEmpsWorkingInADepartmentOnAGivenDate);

        List<SalaryDTO> listOfAllSalaries = salaryDTORepository.getAllSalaries();

        List<SalaryDTO> salariesOfEmployeesWorkingInGivenDepartmentOnGivenDate = listOfAllSalaries.stream().filter(salaryDTO -> listOfEmployeeIdsWorkingInADepartmentOnAGivenDate.contains(salaryDTO.getEmpNo().getId())).toList();

        double sum = 0;
        int i = 0;
        for (i = 0; i < salariesOfEmployeesWorkingInGivenDepartmentOnGivenDate.size(); i++) {
            sum += salariesOfEmployeesWorkingInGivenDepartmentOnGivenDate.get(i).getSalary();
        }
        return sum / i;
    }
}
