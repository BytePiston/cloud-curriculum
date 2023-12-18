package com.cactus.cloud.curriculum.service;

import com.cactus.cloud.curriculum.entity.Department;

import java.util.List;
import java.util.Optional;

public interface IDepartmentService {

    Department saveDepartment(Department department);

    List<Department> fetchAllDepartments();

    Optional<Department> fetchDepartmentById(String id);

    void deleteDepartmentById(String id);

    Optional<Department> updateDepartment(String id, Department department);

    Optional<Department> fetchDepartmentByName(String departmentName);
}
