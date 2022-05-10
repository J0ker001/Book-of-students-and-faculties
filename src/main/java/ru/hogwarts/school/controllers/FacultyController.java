package ru.hogwarts.school.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.Interface.FacultyService;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;


import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity<Faculty> creatFaculty(@RequestBody Faculty faculty) {
        Faculty creatFaculty = facultyService.createFaculty(faculty);
        return ResponseEntity.ok(creatFaculty);
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty editFaculty = facultyService.editFaculty(faculty.getId(), faculty);
        return ResponseEntity.ok(editFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("filter/{color}")
    public List<Faculty> filteredColor(@PathVariable String color) {
        return facultyService.sortFaculties(color);
    }

    @GetMapping("findNAmeORColor/{name},{color}")
    public Collection<Faculty> findByNameOrColor(@RequestParam(required = false)  String name,
                                                 @RequestParam(required = false)  String color) {
        return facultyService.findByNameOrColor(name, color);
    }

    @GetMapping("search/{name}")
    public Collection<Student> findByName(@RequestParam String name) {
        return facultyService.findByName(name);
    }
}
