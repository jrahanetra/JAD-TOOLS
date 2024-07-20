package com.jadteam.jadapi.level;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class LevelService {

    private final LevelRepository levelRepository;
    private static List<Level> levels = new ArrayList<>();

    static {
        Level l1 = new Level("L1");
        Level l2 = new Level("L2");
        Level l3 = new Level("L3");
        Level m1 = new Level("M1");
        Level m2 = new Level("M2");
        levels.add(l1);
        levels.add(l2);
        levels.add(l3);
        levels.add(m1);
        levels.add(m2);
    }

    public LevelService(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;

        for (var level: levels)
            this.levelRepository.save(level);
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
