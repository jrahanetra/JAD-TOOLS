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
    public static List<Major> majors = new ArrayList<>();

    static {
        Major m1 = new Major("Infrastructure cloud et devops");
        Major m2 = new Major("Développement web et application mobile");
        Major m3 = new Major("Intelligence artificielle et big data");
        majors.add(m1);
        majors.add(m2);
        majors.add(m3);
    }

    public MajorService(MajorRepository majorRepository) {
        this.majorRepository = majorRepository;

        for (var major: majors)
            this.majorRepository.save(major);
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

    public MajorDto findMajorDtoById(Integer id) {
        Major major = majorRepository.findById(id).orElse(null);
        return toMajorDto(major);
    }

    public Major findMajorById(Integer id) {
        return majorRepository.findById(id).orElse(null);
    }
    
}
