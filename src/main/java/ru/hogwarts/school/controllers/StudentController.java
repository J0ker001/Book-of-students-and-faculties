package ru.hogwarts.school.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> creatFaculty(@RequestBody Student student) {
        Student creatStudent = studentService.createStudent(student);
        return ResponseEntity.ok(creatStudent);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getFaculty(@PathVariable long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity<Student> editFaculty(@RequestBody Student student) {
        Student editStudent = studentService.editStudent(student);
        return ResponseEntity.ok(editStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        Student deleteStudent = studentService.deleteStudent(id);
        if (deleteStudent == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(deleteStudent);
    }

    @GetMapping("filter/{age}")
    public List<Map.Entry<Long, Student>> filteredColor(@PathVariable Integer age) {
        return studentService.sortStudent(age);
    }
}