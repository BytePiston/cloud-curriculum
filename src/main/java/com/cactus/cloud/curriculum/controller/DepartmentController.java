package com.cactus.cloud.curriculum.controller;

import com.cactus.cloud.curriculum.entity.Department;
import com.cactus.cloud.curriculum.exception.DepartmentNotFoundException;
import com.cactus.cloud.curriculum.service.IDepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/departments")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @GetMapping("")
    public List<Department> fetchAllDepartments(){
        return departmentService.fetchAllDepartments();
    }

    @GetMapping("/{id}")
    public Optional<Department> fetchDepartmentById(@PathVariable("id") String id) throws DepartmentNotFoundException {
        return departmentService.fetchDepartmentById(id);
    }

    @GetMapping("/name/{departmentName}")
    public Optional<Department> fetchDepartmentByName(@PathVariable("departmentName") String departmentName){
        return departmentService.fetchDepartmentByName(departmentName);
    }

    @PostMapping("")
    public Department persistDepartment(@Valid @RequestBody Department department){
        return departmentService.saveDepartment(department);
    }

    @PutMapping("/{id}")
    public Optional<Department> updateDepartmentById(@PathVariable("id") String id, @RequestBody Department department) throws DepartmentNotFoundException {
        return departmentService.updateDepartment(id, department);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteDepartmentById(@PathVariable("id") String id){
        departmentService.deleteDepartmentById(id);
        return HttpStatus.NO_CONTENT;
    }
}
