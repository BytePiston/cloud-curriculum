package com.cactus.cloud.curriculum.service;

import com.cactus.cloud.curriculum.entity.Department;
import com.cactus.cloud.curriculum.exception.DepartmentNotFoundException;
import com.cactus.cloud.curriculum.repository.IDepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentService implements IDepartmentService{

    @Autowired
    private IDepartmentRepository departmentRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentService.class);

    @Override
    public Department saveDepartment(Department department) {
        LOGGER.info("Persisting new Department entity!");
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchAllDepartments() {
        LOGGER.info("Fetching all Departments!");
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> fetchDepartmentById(String id) throws DepartmentNotFoundException {
        LOGGER.info("Fetching Department by ID!");
        Optional<Department> department = departmentRepository.findById(id);
        if(department.isPresent()){
            LOGGER.info("Department Found!");
        } else{
            LOGGER.error("Department NOT Found!");
            throw new DepartmentNotFoundException("Department NOT Found!");
        }
        return department;
    }

    @Override
    public void deleteDepartmentById(String id) {
        LOGGER.info("Deleting Department!");
        departmentRepository.deleteById(id);
    }

    @Override
    public Optional<Department> updateDepartment(String id, Department department) throws DepartmentNotFoundException {
        Optional<Department> departmentFromDB = departmentRepository.findById(id);
        if(departmentFromDB.isPresent() && Objects.nonNull(department)){
            LOGGER.info("Inside Update Department: Department Found!");
//            Department departmentObj = departmentFromDB.get();
            if(department.getDepartmentName() != null && !department.getDepartmentName().isEmpty()){
                departmentFromDB.get().setDepartmentName(department.getDepartmentName());
            }

            if(department.getDepartmentCode() != null && !department.getDepartmentCode().isEmpty()){
                departmentFromDB.get().setDepartmentCode(department.getDepartmentCode());
            }
            if(department.getDepartmentAddress() != null && !department.getDepartmentAddress().isEmpty()){
                departmentFromDB.get().setDepartmentAddress(department.getDepartmentAddress());
            }
            departmentRepository.save(departmentFromDB.get());
            return departmentFromDB;
        }   else{
            LOGGER.error("Inside Update Department: Department NOT Found!");
            throw new DepartmentNotFoundException("Inside Update Department: Department NOT Found!");
        }
    }

    @Override
    public Optional<Department> fetchDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }
}
