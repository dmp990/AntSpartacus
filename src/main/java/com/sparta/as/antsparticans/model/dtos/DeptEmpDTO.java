package com.sparta.as.antsparticans.model.dtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "dept_emp")
public class DeptEmpDTO {
    @EmbeddedId
    private DeptEmpDTOId id;

    @MapsId("deptNo")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "dept_no", nullable = false)
    private DepartmentDTO deptNo;

    @NotNull
    @Column(name = "from_date", nullable = false)
    private LocalDate fromDate;

    @NotNull
    @Column(name = "to_date", nullable = false)
    private LocalDate toDate;

    public DeptEmpDTOId getId() {
        return id;
    }

    public void setId(DeptEmpDTOId id) {
        this.id = id;
    }

    public DepartmentDTO getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(DepartmentDTO deptNo) {
        this.deptNo = deptNo;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

}