package com.cactus.cloud.curriculum.controller;

import com.cactus.cloud.curriculum.entity.Department;
import com.cactus.cloud.curriculum.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @MockBean
    private DepartmentService departmentService;

    @Autowired
    private MockMvc mockMvc;

    private Department department;
    private Department department1;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentId("e7b4e0fa-41fe-4be8-86b0-bcfeee8c0f0a")
                .departmentName("Black Hat Hacking")
                .departmentCode("BHH-18")
                .departmentAddress("Remote")
                .build();

        department1 = Department.builder()
                .departmentId("7940f807-4f5a-4620-8932-05079208d491")
                .departmentName("White")
                .departmentCode("WHH-18")
                .departmentAddress("Remote")
                .build();
    }

    @Test
    void persistDepartmentTest() throws Exception {
        Department inputDepartment = Department.builder()
                .departmentName("Black Hat Hacking")
                .departmentCode("BHH-18")
                .departmentAddress("Remote")
                .build();
        when(departmentService.saveDepartment(inputDepartment)).thenReturn(department);
        mockMvc.perform(post("/api/v1/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"departmentName\":\"Black Hat Hacking\",\n" +
                        "    \"departmentAddress\":\"Remote\",\n" +
                        "    \"departmentCode\":\"BHH-18\"\n" +
                        "}")).andExpect(status().isOk());
    }

    @Test
    void fetchDepartmentByIdTest() throws Exception {
        String departmentId = "e7b4e0fa-41fe-4be8-86b0-bcfeee8c0f0a";
        Department inputDepartment = Department.builder()
                .departmentName("Black Hat Hacking")
                .departmentCode("BHH-18")
                .departmentAddress("Remote")
                .build();
        when(departmentService.fetchDepartmentById(departmentId)).thenReturn(Optional.of(department));
        mockMvc.perform(get("/api/v1/departments/" + departmentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").value(department.getDepartmentName()))
                .andExpect(jsonPath("$.departmentAddress").value(department.getDepartmentAddress()))
                .andExpect(jsonPath("$.departmentCode").value(department.getDepartmentCode()));
    }

    @Test
    void fetchAllDepartmentsTest() throws Exception {
        when(departmentService.fetchAllDepartments()).thenReturn(List.of(department, department1));
        mockMvc.perform(get("/api/v1/departments")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].departmentName").value(department.getDepartmentName()))
                .andExpect(jsonPath("$[0].departmentAddress").value(department.getDepartmentAddress()))
                .andExpect(jsonPath("$[1].departmentName").value(department1.getDepartmentName()))
                .andExpect(jsonPath("$[1].departmentAddress").value(department1.getDepartmentAddress()));
    }

    @Test
    void fetchDepartmentByNameTest() throws Exception {
        when(departmentService.fetchDepartmentByName("white")).thenReturn(Optional.of(department1));
        mockMvc.perform(get("/api/v1/departments/name/white")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.departmentName").value(department1.getDepartmentName()));
    }

//    @Test
//    void updateDepartmentByIdTest() {
//
//    }
//
//    @Test
//    void deleteDepartmentByIdTest() {
//    }
}
