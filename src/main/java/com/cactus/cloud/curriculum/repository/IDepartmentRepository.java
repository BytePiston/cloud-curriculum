package com.cactus.cloud.curriculum.repository;

import com.cactus.cloud.curriculum.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDepartmentRepository extends JpaRepository<Department, String> {

    Optional<Department> findByDepartmentName(String departmentName);
    Optional<Department> findByDepartmentNameIgnoreCase(String departmentName);
}
