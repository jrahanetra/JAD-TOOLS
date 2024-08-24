package com.jadteam.jadapi.studentimage;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * StudentImageController
 */
@RestController
@RequestMapping("/studentimages")
public class StudentImageController {
    
	private final StudentImageService studentImageService;

    public StudentImageController(StudentImageService studentImageService) {
        this.studentImageService = studentImageService;
    }

    @PostMapping("")
    public String addStudentImage(@RequestBody MultipartFile image) throws IOException {
        return studentImageService.uploadImage(image);
    }

    @GetMapping("")
    public byte[] getImageByName(@RequestParam String name) {
        return studentImageService.downloadImage(name);
    }

}
