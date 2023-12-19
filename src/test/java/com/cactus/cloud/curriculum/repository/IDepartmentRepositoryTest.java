package com.cactus.cloud.curriculum.repository;

import com.cactus.cloud.curriculum.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IDepartmentRepositoryTest {

    @Autowired
    private IDepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("CS")
                .departmentAddress("Edmonton")
                .departmentCode("CS18")
                .build();

        entityManager.persist(department);
    }

    @Test
    public void findByDepartmentNameIgnoreCaseTest(){
        Optional<Department> department = departmentRepository.findByDepartmentNameIgnoreCase("cs");
        assertTrue(department.isPresent());
        assertEquals(department.get().getDepartmentName(), "CS");
        assertEquals(department.get().getDepartmentCode(), "CS18");
        assertEquals(department.get().getDepartmentAddress(), "Edmonton");
    }
}
