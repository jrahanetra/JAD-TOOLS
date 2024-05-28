package com.jad.jadapi.Students;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * StudentRepository
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {
    
	
}
