package ru.hogwarts.school.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.Interface.FacultyService;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;


import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping("/createNew")
    public ResponseEntity<Faculty> creatFaculty(@RequestBody Faculty faculty) {
        Faculty creatFaculty = facultyService.createFaculty(faculty);
        return ResponseEntity.ok(creatFaculty);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping("/update")
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty editFaculty = facultyService.editFaculty(faculty);
        return ResponseEntity.ok(editFaculty);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("filter/{color}")
    public List<Faculty> filteredColor(@PathVariable String color) {
        return facultyService.sortFaculties(color);
    }

    @GetMapping("findNAmeORColor")
    public Collection<Faculty> findByNameOrColor(@RequestParam(required = false)  String name,
                                                 @RequestParam(required = false)  String color) {
        return facultyService.findByNameOrColor(name, color);
    }

    @GetMapping("search")
    public Collection<Student> findByName(@RequestParam String name) {
        return facultyService.findByName(name);
    }
}
