package com.jadteam.jadapi.registration;

import java.util.List;

import com.jadteam.jadapi.level.Level;
import com.jadteam.jadapi.major.Major;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RegistrationRepository
 */
public interface RegistrationRepository extends JpaRepository<Registration, RegistrationId> {
    
    public List<Registration> findAllByMajorAndLevel(Major major, Level level);
}
