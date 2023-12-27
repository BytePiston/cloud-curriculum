package com.cactus.cloud.curriculum.repository;

import com.cactus.cloud.curriculum.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IStudentRepository extends JpaRepository<Student, String> {

    List<Student> findByFirstName(String firstName);

    List<Student> findByFirstNameContaining(String firstName);

    List<Student> findByGuardianName(String guardianName);

    List<Student> findByGuardianNameContaining(String guardianName);

    @Modifying
    @Transactional
    @Query("Update Student s set s.firstName = ?1 where s.email = ?2")
    int updateStudentNameByEmail(String firstName, String email);

    @Modifying
    @Transactional
    @Query("Update Student s set s.firstName = ?1 where s.id = ?2")
    int updateStudentNameById(String firstName, String id);
}
