package com.jadteam.jadapi.studentimage;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StudentImageService {

    private final StudentImageRepository studentImageRepository;

    public StudentImageService(StudentImageRepository studentImageRepository) {
        this.studentImageRepository = studentImageRepository;
    }

    public String uploadImage(MultipartFile imageFile) throws IOException {
        StudentImage studentImage = new StudentImage(imageFile.getOriginalFilename(), imageFile.getContentType(), imageFile.getBytes());
        studentImageRepository.save(studentImage);
        return "file uploaded successfully : " + imageFile.getOriginalFilename();
    }

    public byte[] downloadImage(String imageName) {
        Optional<StudentImage> dbImage = studentImageRepository.findByName(imageName);
        return dbImage.get().getImageData();
    }
}
