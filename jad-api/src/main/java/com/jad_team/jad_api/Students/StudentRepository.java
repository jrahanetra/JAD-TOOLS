package com.jad_team.jad_api.Students;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * StudentRepository
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {
    
	
}
