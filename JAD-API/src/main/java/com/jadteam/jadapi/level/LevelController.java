package com.jadteam.jadapi.level;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/levels")
public class LevelController {

    private final LevelService levelService;

    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping("")
    public List<Level> findAllLevel() {
        return levelService.findAllLevel();
    }
    
}
