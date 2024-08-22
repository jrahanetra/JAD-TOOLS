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
            throw new NullPointerException("The Level is null.");
        LevelDto levelDto = new LevelDto(level.getLevelId(), level.getLevelName());
        return levelDto;
    }

    public Level toLevel(LevelDto levelDto) {
        if (levelDto == null)
            throw new NullPointerException("The LevelDto is null.");
        Level level = new Level(levelDto.levelName());
        level.setLevelId(levelDto.levelId());
        return level;
    }

    public List<LevelDto> findAllLevel() {
        List<Level> levels = levelRepository.findAll();
        List<LevelDto> levelDtos = new ArrayList<>();
        for (var level: levels)
            levelDtos.add(toLevelDto(level));
        return levelDtos;
    }

    public LevelDto findLevelDtoById(Integer id) {
        Level level = levelRepository.findById(id).orElse(null);
        return toLevelDto(level);
    }

    public Level findLevelById(Integer id) {
        return levelRepository.findById(id).orElse(null);
    }
    
}
