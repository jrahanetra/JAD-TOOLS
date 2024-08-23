package com.jadteam.jadapi.studentcourse;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * StudentCourseController
 */
@RestController
@RequestMapping("/studentcourses")
public class StudentCourseController {
    
    private final StudentCourseService studentCourseService;

    public StudentCourseController(StudentCourseService studentCourseService) {
        this.studentCourseService = studentCourseService;
    }

    @PostMapping("")
    public StudentCourse addStudentCourse(@RequestBody String json) throws JsonProcessingException, JsonMappingException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(json);
        JsonNode jsonNode = objectMapper.readTree(json);
        if (jsonNode.get("studentId") == null ||
            jsonNode.get("courseId") == null ||
            jsonNode.get("attending") == null ||
            jsonNode.get("justificated") == null)
            throw new NullPointerException("One field in the Json is missing..");
        Integer studentId = jsonNode.get("studentId").asInt();
        Integer courseId = jsonNode.get("courseId").asInt();
        Boolean attending = jsonNode.get("attending").asInt() == 1;
        Boolean justificated = jsonNode.get("justificated").asInt() == 1;
        return studentCourseService.saveStudentCourse(studentId, courseId, attending, justificated);
    }

    @GetMapping("")
    public List<StudentCourseDto> findAllStudentCourses() {
        return studentCourseService.findAllStudentCourses();
    }

    @GetMapping("/d")
    public List<StudentCourseDto> findAllStudentCoursesByDate(@RequestBody LocalDate date) {
        return studentCourseService.findAllStudentCoursesByDate(date);
    }

    @GetMapping("/s")
    public List<StudentCourseDto> findAllStudentCoursesByStudentId(@RequestParam Integer StudentId) {
        return studentCourseService.findAllStudentCoursesByStudentId(StudentId);
    }
    
    
}
