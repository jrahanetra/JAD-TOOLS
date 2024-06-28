package com.jadteam.jadapi.registration;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RegistrationRepository
 */
public interface RegistrationRepository extends JpaRepository<Registration, RegistrationId> {
    
	
}
