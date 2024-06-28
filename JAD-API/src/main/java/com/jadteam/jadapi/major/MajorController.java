package com.jadteam.jadapi.major;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MajorController
 */
@RestController
@RequestMapping("/majors")
public class MajorController {

    private final MajorService majorService;

    public MajorController(MajorService majorService) {
        this.majorService = majorService;
    }

	@GetMapping("")
    public List<Major> findAllLevel() {
        return majorService.findAllMajor();
    }

    @GetMapping("/{id}")
    public Major findLevelById(@PathVariable("id") Integer id) {
        return majorService.findMajorById(id);
    }

    @PostMapping("")
    public Major addMajor(@RequestBody Major major) {
        return majorService.saveMajor(major);
    }
    

}
