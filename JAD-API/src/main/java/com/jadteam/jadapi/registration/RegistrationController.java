package com.jadteam.jadapi.registration;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * RegistrationController
 */
@RestController
@RequestMapping("/registrations")
public class RegistrationController {
    
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("")
    public RegistrationDto addRegistration(@RequestParam("studentId") Integer studentId,
                                           @RequestParam("majorId") Integer majorId,
                                           @RequestParam("levelId") Integer levelId,
                                           @RequestParam("year") Integer year) {
        return registrationService.saveRegistration(studentId, majorId, levelId, year);
    }

    @GetMapping("")
    public List<RegistrationDto> findAllRegistrations() {
        return registrationService.findAllRegistrations();
    }

    @GetMapping("/s")
    public List<RegistrationDto> findAllRegistrationsByMajorAndLevel(@RequestParam("majorId") Integer majorId,
                                                                     @RequestParam("levelId") Integer levelId) {
        return registrationService.findAllRegistrationsByMajorAndLevel(majorId, levelId);
    }

}
