package ru.igojig.lesson_7.spring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.igojig.lesson_7.spring.entity.Student;
import ru.igojig.lesson_7.spring.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public List<Student> getAll() {
        List<Student> students = studentService.getAll();
        return students;
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable Long id) {
        Student student = studentService.findById(id);
        return student;
    }

    @PutMapping
    public Student update(@RequestBody Student student) {
        return studentService.update(student);
    }

    @PostMapping
    public Student saveNew(@RequestBody Student student) {
        return studentService.saveNew(student);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll() {
        studentService.deleteAll();
    }
}
