package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.Interface.FacultyService;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service("faculty")
public class FacultyServiceImpl implements FacultyService {


    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;

    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findFaculty(Long id) {
        return facultyRepository.findById(id).get();
    }

    @Override
    public Faculty editFaculty(Long lastID, Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    @Override
    public List<Faculty> sortFaculties(String color) {
        return facultyRepository.findAll().stream().filter((p) -> p.getColor().equals(color)).
                collect(Collectors.toList());
    }

    @Override
    public Collection<Faculty> findByNameOrColor(String name, String color) {
        return facultyRepository.findByNameOrColorAllIgnoreCase(name, color);
    }

    @Override
    public Collection<Student> findByName(String name) {
        return facultyRepository.findByName(name).getStudents();
    }

}

