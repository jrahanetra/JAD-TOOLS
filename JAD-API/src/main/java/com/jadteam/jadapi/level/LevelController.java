package com.jadteam.jadapi.level;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public List<LevelDto> findAllLevel() {
        return levelService.findAllLevel();
    }

    @GetMapping("/{id}")
    public Level findLevelById(@PathVariable("id") Integer id) {
        return levelService.findLevelById(id);
    }

}
