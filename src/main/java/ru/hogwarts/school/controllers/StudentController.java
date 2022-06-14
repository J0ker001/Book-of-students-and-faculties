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

//создание студента    
    @PostMapping("/creatNew")
    public ResponseEntity<Student> creatStudent(@RequestBody Student student) {
        Student creatStudent = studentService.createStudent(student);
        return ResponseEntity.ok(creatStudent);
    }

//получить студента по id
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

//обновить данные студента
    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student editStudent = studentService.editStudent(student);
        return ResponseEntity.ok(editStudent);
    }

//удалить студента    
    @DeleteMapping("/delete{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

//получить список студентов, отсортированных по возрасту    
    @GetMapping("filter{age}")
    public List<Student> filteredStudent(@RequestParam Integer age) {
        return studentService.sortStudent(age);
    }

//получить список студентов, которые в возрасте от * и до *
    @GetMapping("between")
    public Collection<Student> findByAgeBetween(@RequestParam("minAge") Integer minAge,
                                                @RequestParam("maxAge") Integer maxAge) {
        return studentService.findByAgeBetween(minAge, maxAge);
    }

//натйти факультет студента, по имени    
    @GetMapping("facult/name")
    public Student findByFacultyId(@RequestParam("name") String name) {
        return studentService.findByName(name);
    }

//кол-во студентов    
    @GetMapping("/amountStudents")
    public Integer amountStudents() {
        return studentService.amountStudents();
    }

//Средний возраст студентов    
    @GetMapping("/avgAge")
    public Integer avgAge() {
        return studentService.avgAge();
    }

//список, последних 5ти студентов    
    @GetMapping("/lastFiveStudents")
    public Collection<Student> lastFiveStudents() {
        return studentService.lastFiveStudents();
    }

//список всех студентов, имя начинющиеся на А в сортировоном порядке    
    @GetMapping("/getAllStudentStartASort")
    public List<String> getAllStudentStartASort() {
        return studentService.getAllStudentStartASort();
    }

//средний возраст студентов    
    @GetMapping("/getAvgAgeStudents")
    public double getAvgAgeStudents() {
        return studentService.getAvgAgeStudents();
    }

//паралельный поток    
    @GetMapping("/parallelFlow")
    public void parallelFlow() {
        studentService.parallelFlow();
    }
    
//синхронизированный параллельный поток     
    @GetMapping("/parallelFlowSynchronized()")
    public void parallelFlowSynchronized() {
        studentService.parallelFlowSynchronized();
    }
}

