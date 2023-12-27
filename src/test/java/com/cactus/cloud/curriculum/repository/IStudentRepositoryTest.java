package com.cactus.cloud.curriculum.repository;

import com.cactus.cloud.curriculum.entity.Guardian;
import com.cactus.cloud.curriculum.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DataJpaTest
//@SpringBootTest
class IStudentRepositoryTest {

    @MockBean
    private IStudentRepository studentRepository;

    Student student1;
    Student student2;

    @BeforeEach
    void setUp() {
        student1 = Student.builder()
                .firstName("Cactus")
                .lastName("Jack")
                .email("spikeme@cactus.com")
                .build();

        Guardian guardian = Guardian.builder()
                .name("guardian")
                .email("guardian@gmail.com")
                .mobile("1234567890")
                .build();

        student2 = Student.builder()
                .firstName("CacGua")
                .lastName("J")
                .email("cacgua@cactus.com")
                .guardian(guardian)
                .build();
    }

/*
    TODO: Update below test cases based on Mocked beans.
    @Test
    public void persistStudentWithoutGuardianTest(){
        studentRepository.save(student1);
    }

    @Test
    public void persistStudentWithGuardianTest(){
        studentRepository.save(student2);
    }
*/

    @Test
    public void fetchAllStudentsTest() {
        when(studentRepository.findAll()).thenReturn(List.of(student1, student2));
        List<Student> retrievedStudentList = studentRepository.findAll();
        assertFalse(retrievedStudentList.isEmpty());
        assertEquals(retrievedStudentList.size(), 2);
    }

    @Test
    void findByFirstNameTest() {
        when(studentRepository.findByFirstName(anyString())).thenReturn(List.of(student1));
        List<Student> retrievedStudentList = studentRepository.findByFirstName("Cactus");
        assertFalse(retrievedStudentList.isEmpty());
        assertEquals(retrievedStudentList.get(0).getFirstName(), student1.getFirstName());
    }

    @Test
    void findByFirstNameContainingTest() {
        when(studentRepository.findByFirstNameContaining(anyString())).thenReturn(List.of(student1));
        List<Student> retrievedStudentList = studentRepository.findByFirstNameContaining("ctu");
        assertFalse(retrievedStudentList.isEmpty());
        assertEquals(retrievedStudentList.get(0).getFirstName(), student1.getFirstName());
    }

    @Test
    void findByGuardianNameTest() {
        when(studentRepository.findByGuardianName(anyString())).thenReturn(List.of(student2));
        List<Student> retrievedStudentList = studentRepository.findByGuardianName("guardian");
        assertFalse(retrievedStudentList.isEmpty());
        assertEquals(retrievedStudentList.get(0).getGuardian().getName(), student2.getGuardian().getName());
    }

    @Test
    public void findByGuardianNameContainingTest() {
        when(studentRepository.findByGuardianNameContaining(anyString())).thenReturn(List.of(student2));
        List<Student> retrievedStudentList = studentRepository.findByGuardianNameContaining("gu");
        assertFalse(retrievedStudentList.isEmpty());
        assertEquals(retrievedStudentList.get(0).getGuardian().getName(), student2.getGuardian().getName());
    }

    @Test
    void updateStudentNameByEmailTest() {
        when(studentRepository.updateStudentNameByEmail(anyString(), anyString())).thenReturn(1);
        assertEquals(studentRepository.updateStudentNameByEmail("NeonKnight", "cacgua@cactus.com"), 1);
    }

    @Test
    void updateStudentNameByIdTest() {
        when(studentRepository.findByFirstNameContaining(anyString())).thenReturn(List.of(student2));
        when(studentRepository.updateStudentNameById(anyString(), anyString())).thenReturn(1);
        List<Student> retrievedStudentList = studentRepository.findByFirstNameContaining("neo");
        assertFalse(retrievedStudentList.isEmpty());
        assertEquals(studentRepository.updateStudentNameById("Neo", "test"), 1);
    }
}

