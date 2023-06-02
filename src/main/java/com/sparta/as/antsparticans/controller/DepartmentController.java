package com.sparta.as.antsparticans.controller;

import com.sparta.as.antsparticans.exceptions.DepartmentNameAlreadyExistsException;
import com.sparta.as.antsparticans.exceptions.DepartmentNotFoundException;
import com.sparta.as.antsparticans.logging.FileHandlerConfig;
import com.sparta.as.antsparticans.model.dtos.DepartmentDTO;
import com.sparta.as.antsparticans.model.repositories.DepartmentDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class DepartmentController {

    DepartmentDTORepository departmentDTORepository;

    private static final Logger departmentControllerLogger = Logger.getLogger(EmployeesController.class.getName());

    static {
        departmentControllerLogger.setUseParentHandlers(false);
        departmentControllerLogger.setLevel(Level.ALL);
        departmentControllerLogger.addHandler(FileHandlerConfig.getFileHandler());
    }

    @Autowired

    public DepartmentController(DepartmentDTORepository departmentDTORepository) {
        this.departmentDTORepository = departmentDTORepository;
        departmentControllerLogger.log(Level.INFO, "Department controller constructor - departmentDTO dependency created");
    }

    @GetMapping("/departments")
    public List<DepartmentDTO> getAllDepartments() {
        departmentControllerLogger.log(Level.INFO, "GET /departments request made");
        return departmentDTORepository.getAllDepartments();
    }

    @GetMapping("/departments/{dept_no}")
    public DepartmentDTO getDepartmentByDeptNo(@PathVariable String dept_no) throws DepartmentNotFoundException {
        departmentControllerLogger.log(Level.INFO, "GET /departments/" + dept_no + " request made");
        return departmentDTORepository.getDepartmentByDeptNo(dept_no).orElseThrow(() -> new DepartmentNotFoundException(dept_no));
    }

    @PutMapping("/departments/{dept_no}")
    public DepartmentDTO changeDepartmentName(@RequestBody DepartmentDTO departmentDTO, @PathVariable String dept_no) throws DepartmentNotFoundException, DepartmentNameAlreadyExistsException {

        String departmentName = departmentDTO.getDeptName();

        departmentControllerLogger.log(Level.INFO, "PUT /departments/" + dept_no + " changeDepartmentName request made with department name" + departmentName);

        if(departmentDTORepository.getDepartmentByDeptName(departmentName).isPresent()) {
            throw new DepartmentNameAlreadyExistsException(departmentName);
        } else {
            if (departmentDTORepository.getDepartmentByDeptNo(dept_no).isPresent()) {
                DepartmentDTO oldDepartmentDTO = departmentDTORepository.getDepartmentByDeptNo(dept_no).get();
                departmentControllerLogger.log(Level.INFO, "PUT /departments/" + dept_no + " changeDepartmentName request made with department name " + departmentName + " replacing department " + dept_no + " " + oldDepartmentDTO.getDeptName());
                oldDepartmentDTO.setDeptName(departmentDTO.getDeptName());
                return departmentDTORepository.save(oldDepartmentDTO);
            } else {
                throw new DepartmentNotFoundException(dept_no);
            }
        }
    }

    @PostMapping("/departments")
    public DepartmentDTO addDepartment(@RequestBody DepartmentDTO departmentDTO) throws DepartmentNameAlreadyExistsException {
        String newDepartmentName = departmentDTO.getDeptName();
        Optional<DepartmentDTO> department = departmentDTORepository.getDepartmentByDeptName(newDepartmentName);
        if(department.isPresent()) {
            throw new DepartmentNameAlreadyExistsException(departmentDTO.getDeptName());
        }
        return departmentDTORepository.save(departmentDTO);
    }

//    @DeleteMapping("/departments")
//    public void deleteADepartment(@RequestBody String deptNo) throws DepartmentNotFoundException {
//        Optional<DepartmentDTO> departmentDTO = departmentDTORepository.getDepartmentByDeptNo(deptNo);
////        if(departmentDTO.isPresent()) {
//            departmentDTORepository.delete(departmentDTO);
////       } else {
////            throw new DepartmentNotFoundException(deptNo);
////        }
//        //return "Department successfully removed";
//    }
}
