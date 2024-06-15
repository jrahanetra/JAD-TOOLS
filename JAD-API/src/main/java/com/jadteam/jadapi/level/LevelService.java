package com.jadteam.jadapi.level;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class LevelService {

    private LevelRepository levelRepository;
    private static List<Level> levelList = new ArrayList<>();

    static {
        Level l1 = new Level();
        l1.setName("L1");
        Level l2 = new Level();
        l2.setName("L2");
        Level l3 = new Level();
        l3.setName("L3");
        Level m1 = new Level();
        m1.setName("M1");
        Level m2 = new Level();
        m2.setName("M2");
        levelList.add(l1);
        levelList.add(l2);
        levelList.add(l3);
        levelList.add(m1);
        levelList.add(m2);
    }

    public LevelService(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
        for (var level: levelList)
            this.levelRepository.save(level);
    }

    public List<Level> findAllLevel() {
        return levelRepository.findAll();
    }

    public Level findLevelById(Integer id) {
        return levelRepository.findById(id).orElse(null);
    }
    
}
