package com.jadteam.jadapi.studentimage;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * StudentImageRepository
 */
public interface StudentImageRepository extends JpaRepository<StudentImage, Integer> {
    
    Optional<StudentImage> findByName(String name);
}
