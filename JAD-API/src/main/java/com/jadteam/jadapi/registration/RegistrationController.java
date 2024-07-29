package com.jadteam.jadapi.registration;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public RegistrationDto addRegistration(@RequestBody Map<String, Integer> map) {
        Integer studentId = map.get("studentId");
        Integer majorId = map.get("majorId");
        Integer levelId = map.get("levelId");
        Integer year = map.get("year");
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
