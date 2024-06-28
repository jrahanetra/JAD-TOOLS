package com.jadteam.jadapi.registration;

/**
 * RegistrationService
 */
public class RegistrationService {
    
    private final RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public Registration saveRegistration(Registration registration) {
        return registrationRepository.save(registration);
    }
}
