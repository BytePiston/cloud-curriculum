package com.cactus.cloud.curriculum.service;

import com.cactus.cloud.curriculum.entity.Department;
import com.cactus.cloud.curriculum.exception.DepartmentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@WebMvcTest(DepartmentService.class)
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentService departmentRepository;

    @BeforeEach
    void setUp() throws DepartmentNotFoundException {
        Department department1 = Department.builder()
                .departmentId("26587317-202d-4b02-ba25-c038462ba97e")
                .departmentName("IT")
                .departmentAddress("address1")
                .departmentCode("IT01")
                .build();

        Department department2 = Department.builder()
                .departmentId("1e2c3d37-622e-4f6a-aede-d7920dcadf30")
                .departmentName("CS")
                .departmentAddress("address2")
                .departmentCode("CS18")
                .build();

        Mockito.when(departmentRepository.fetchDepartmentByName("IT")).thenReturn(Optional.of(department1));
        Mockito.when(departmentRepository.fetchAllDepartments()).thenReturn(List.of(department1, department2));
        Mockito.when(departmentRepository.fetchDepartmentById(Mockito.anyString())).thenReturn(Optional.of(department1));
        Mockito.when(departmentRepository.updateDepartment(department1.getDepartmentId(), department1)).thenReturn(Optional.of(department1));
    }

    @Test
    @DisplayName("Positive scn: Fetch Department By Name")
    public void fetchDepartmentByNameTest() {
        String departmentName = "IT";
        Optional<Department> retrivedDepartment = departmentService.fetchDepartmentByName(departmentName);
        assertTrue(retrivedDepartment.isPresent());
        assertEquals(departmentName, retrivedDepartment.get().getDepartmentName());
    }

    @Test
    @DisplayName("Positive scn: Fetch All Departments")
    void fetchAllDepartmentsTest() {
        List<Department> retrivedDepartmentList = departmentService.fetchAllDepartments();
        assertEquals(retrivedDepartmentList.size(), 2);
        assertEquals(retrivedDepartmentList.get(0).getDepartmentName(), "IT");
        assertEquals(retrivedDepartmentList.get(1).getDepartmentName(), "CS");
    }

    @Test
    @DisplayName("Positive scn: Fetch Department By Id")
    void fetchDepartmentByIdTest() throws DepartmentNotFoundException {
        String departmentId = "26587317-202d-4b02-ba25-c038462ba97e";
        Optional<Department> retrivedDepartment = departmentService.fetchDepartmentById(departmentId);
        assertTrue(retrivedDepartment.isPresent());
        assertEquals(retrivedDepartment.get().getDepartmentId(), departmentId);
    }

    @Test
    @DisplayName("Positive scn: Update Department")
    void updateDepartmentTest() throws DepartmentNotFoundException {
        String departmentId = "26587317-202d-4b02-ba25-c038462ba97e";
        Optional<Department> retrivedDepartment = departmentService.fetchDepartmentById(departmentId);
        assertTrue(retrivedDepartment.isPresent());
        retrivedDepartment.get().setDepartmentName("Updated_Name");
        Optional<Department> updatedDepartment = departmentService.updateDepartment(departmentId, retrivedDepartment.get());
        assertTrue(updatedDepartment.isPresent());
        assertEquals("Updated_Name", updatedDepartment.get().getDepartmentName());
    }
}
