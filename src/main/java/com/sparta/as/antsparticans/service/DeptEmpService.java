package com.sparta.as.antsparticans.service;

import com.sparta.as.antsparticans.model.dtos.DepartmentDTO;
import com.sparta.as.antsparticans.model.dtos.DeptEmpDTO;
import com.sparta.as.antsparticans.model.repositories.DepartmentDTORepository;
import com.sparta.as.antsparticans.model.repositories.DeptEmpDTORepository;
import com.sparta.as.antsparticans.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DeptEmpService {

    public DepartmentDTORepository departmentDTORepository;
    public DeptEmpDTORepository deptEmpDTORepository;

    @Autowired
    public DeptEmpService(DepartmentDTORepository departmentDTORepository, DeptEmpDTORepository deptEmpDTORepository) {
        this.departmentDTORepository = departmentDTORepository;
        this.deptEmpDTORepository = deptEmpDTORepository;
    }

    public List<DeptEmpDTO> getListOfDeptEmpsWorkingInADepartmentOnAGivenDate(String deptName, LocalDate date) {
        /*
        Returns the list of dept-emp who have worked in "deptName" on "date"
         */
        DepartmentDTO dept = departmentDTORepository.findByDeptName(deptName);
        List<DeptEmpDTO> array = deptEmpDTORepository.findByDeptNo(dept);

        // employees who have worked in given department
        return array.stream().filter(deptEmpDTO ->
                Utils.isDateWithin(date, deptEmpDTO.getFromDate(), deptEmpDTO.getToDate())
        ).toList();
    }

}
