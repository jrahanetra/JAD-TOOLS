package com.jadteam.jadapi.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * StudentRepository
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Modifying
    @Query("update Student s set s.firstName = :firstName, s.lastName = :lastName, s.address = :address, s.email = :email, s.phoneNumber = :phoneNumber where s.id = :id")
	String updateStudent(
                       @Param("id") Integer id, 
                       @Param("firstName") String firstName, 
                       @Param("lastName") String lastName, 
                       @Param("address") String address, 
                       @Param("email") String email, 
                       @Param("phoneNumber") String phoneNumber
    );
}
