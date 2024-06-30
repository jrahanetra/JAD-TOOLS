package com.jadteam.jadapi.level;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class LevelService {

    private final LevelRepository levelRepository;

    public LevelService(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    public LevelDto toLevelDto(Level level) {
        if (level == null)
            throw new NullPointerException("L'objet niveau est null.");
        LevelDto levelDto = new LevelDto(level.getLevelId(), level.getLevelName());
        return levelDto;
    }

    public List<LevelDto> findAllLevel() {
        List<Level> levels = levelRepository.findAll();
        List<LevelDto> levelDtos = new ArrayList<>();
        for (var level: levels)
            levelDtos.add(toLevelDto(level));
        return levelDtos;
    }

    public Level findLevelById(Integer id) {
        return levelRepository.findById(id).orElse(null);
    }
    
}
