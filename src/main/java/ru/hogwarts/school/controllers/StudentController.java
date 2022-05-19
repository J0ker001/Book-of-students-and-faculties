package ru.hogwarts.school.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.Interface.StudentService;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/creatNew")
    public ResponseEntity<Student> creatStudent(@RequestBody Student student) {
        Student creatStudent = studentService.createStudent(student);
        return ResponseEntity.ok(creatStudent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student editStudent = studentService.editStudent(student);
        return ResponseEntity.ok(editStudent);
    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("filter{age}")
    public List<Student> filteredStudent(@RequestParam Integer age) {
        return studentService.sortStudent(age);
    }

    @GetMapping("between")
    public Collection<Student> findByAgeBetween(@RequestParam("minAge") Integer minAge,
                                                @RequestParam("maxAge") Integer maxAge) {
        return studentService.findByAgeBetween(minAge, maxAge);
    }

    @GetMapping("facult/name")
    public Student findByFacultyId(@RequestParam("name") String name) {
        return studentService.findByName(name);
    }

    @GetMapping("/amountStudents")
    public Integer amountStudents() {
        return studentService.amountStudents();
    }

    @GetMapping("/avgAge")
    public Integer avgAge() {
        return studentService.avgAge();
    }

    @GetMapping("/lastFiveStudents")
    public Collection<Student> lastFiveStudents() {
        return studentService.lastFiveStudents();
    }
}