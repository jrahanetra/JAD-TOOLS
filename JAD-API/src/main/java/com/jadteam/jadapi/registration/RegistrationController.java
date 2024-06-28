package com.jadteam.jadapi.registration;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * RegistrationController
 */
@RequestMapping("/registrations")
public class RegistrationController {
    
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("")
    public Registration addRegistration(@RequestBody Registration registration) {
        return registrationService.saveRegistration(registration);
    }
    
}
