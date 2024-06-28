package com.jadteam.jadapi.major;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * MajorService
 */
@Service
public class MajorService {
    
    private final MajorRepository majorRepository;
    private static List<Major> majorList = new ArrayList<>();

    static {
        Major major1 = new Major("Infrastructures cloud et devops");
        Major major2 = new Major("Développement web et application mobile");
        Major major3 = new Major("Intelligence artificielle et big data");
        majorList.add(major1);
        majorList.add(major2);
        majorList.add(major3);
    }

    public MajorService(MajorRepository majorRepository) {
        this.majorRepository = majorRepository;
    }

    public List<Major> findAllMajor() {
        return majorRepository.findAll();
    }

    public Major findMajorById(Integer id) {
        return majorRepository.findById(id).orElse(null);
    }

    public Major saveMajor(Major major) {
        return majorRepository.save(major);
    }
    
}
