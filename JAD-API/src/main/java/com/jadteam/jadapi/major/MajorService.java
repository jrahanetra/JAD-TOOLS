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

    public MajorService(MajorRepository majorRepository) {
        this.majorRepository = majorRepository;
    }

    public MajorDto toMajorDto(Major major) {
        if (major == null)
            throw new NullPointerException("L'objet parcours est null.");
        MajorDto majorDto = new MajorDto(major.getMajorId(), major.getMajorName());
        return majorDto;
    }

    public MajorDto saveMajor(Major major) {
        if (major == null)
            throw new NullPointerException("Les informations du parcours sont invalides.");
        majorRepository.save(major);
        return toMajorDto(major);
    }

    public List<MajorDto> findAllMajor() {
        List<Major> majors = majorRepository.findAll();
        List<MajorDto> majorDtos = new ArrayList<>();
        for (var major: majors)
            majorDtos.add(toMajorDto(major));
        return majorDtos;
    }

    public Major findMajorById(Integer id) {
        return majorRepository.findById(id).orElse(null);
    }
    
}
