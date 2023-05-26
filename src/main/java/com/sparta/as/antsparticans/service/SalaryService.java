package com.sparta.as.antsparticans.service;

import com.sparta.as.antsparticans.model.dtos.DeptEmpDTO;
import com.sparta.as.antsparticans.model.dtos.SalaryDTO;
import com.sparta.as.antsparticans.model.dtos.TitleDTO;
import com.sparta.as.antsparticans.model.repositories.DepartmentDTORepository;
import com.sparta.as.antsparticans.model.repositories.DeptEmpDTORepository;
import com.sparta.as.antsparticans.model.repositories.SalaryDTORepository;
import com.sparta.as.antsparticans.model.repositories.TitleDTORepository;
import com.sparta.as.antsparticans.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class SalaryService {

    public final DeptEmpDTORepository deptEmpDTORepository;

    private final DepartmentDTORepository departmentDTORepository;

    private final SalaryDTORepository salaryDTORepository;

    private final TitleDTORepository titleDTORepository;

    @Autowired
    public SalaryService(DeptEmpDTORepository deptEmpDTORepository, DepartmentDTORepository departmentDTORepository, SalaryDTORepository salaryDTORepository, TitleDTORepository titleDTORepository) {
        this.deptEmpDTORepository = deptEmpDTORepository;
        this.departmentDTORepository = departmentDTORepository;
        this.salaryDTORepository = salaryDTORepository;
        this.titleDTORepository = titleDTORepository;
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

    /*
    Given a job title name, what is the range of salary values within a given year?
     */

    public String getRangeOfSalaryWithinAYearGivenAJobTitle(String jobTitle, LocalDate beginDate, LocalDate endDate) {
        List<SalaryDTO> salaryDTOS = salaryDTORepository.getAllSalariesWithinADateRangeForAGivenTitle(jobTitle, beginDate, endDate);

        Collections.sort(salaryDTOS);

        return salaryDTOS.size() > 0 ? "Min: " + salaryDTOS.get(0).getSalary() + ", Max: " + salaryDTOS.get(salaryDTOS.size() - 1).getSalary() : "No Salaries found for the given parameters";


    /*
        TitleService titleService = new TitleService(titleDTORepository);

        List<TitleDTO> filteredTitleDtos = titleService.getAllTitlesDtosByTitleWorkingDuringAGivenRange(jobTitle, beginDate, endDate);

        List<Integer> listOfDesiredEmployeeNos = Utils.extractEmployeeIdsFromListOfTitleDTOs(filteredTitleDtos);

        List<SalaryDTO> listOfAllSalaries = salaryDTORepository.getAllSalariesWithinARange(beginDate, endDate);

        List<SalaryDTO> listOfDesiredSalaries = new java.util.ArrayList<>(listOfAllSalaries.stream().filter(salaryDTO ->
                listOfDesiredEmployeeNos.contains(salaryDTO.getEmpNo().getId())
        ).toList());

        Collections.sort(listOfDesiredSalaries);

        System.out.println(listOfDesiredSalaries.get(0).getEmpNo().getId() + " <<<");

        return listOfDesiredSalaries.size() > 0 ? "Min: " + listOfDesiredSalaries.get(0).getSalary() + ", Max: " + listOfDesiredSalaries.get(listOfDesiredSalaries.size() - 1).getSalary() : "No Salaries found for the given parameters";

        */
    }

}
