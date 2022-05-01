package ru.hogwarts.school.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyServiceImpl;

import java.util.List;


@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyServiceImpl facultyService;

    public FacultyController(FacultyServiceImpl facultyService) {
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
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id) {
        Faculty deleteFaculty = facultyService.deleteFaculty(id);
        if (deleteFaculty == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(deleteFaculty);
    }

    @GetMapping("filter/{color}")
    public List<Faculty> filteredColor(@PathVariable String color) {
        return facultyService.sortFaculties(color);
    }
}
