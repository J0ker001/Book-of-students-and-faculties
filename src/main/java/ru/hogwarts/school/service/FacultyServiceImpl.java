package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.hogwarts.school.Interface.FacultyService;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;


import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service("faculty")
public class FacultyServiceImpl implements FacultyService {
    Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;

    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        logger.info("Использован метод, createFaculty");
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findFaculty(Long id) {
        logger.info("Использован метод, findFaculty");
        return facultyRepository.findById(id).get();
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
        logger.info("Использован метод, editFaculty");
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(Long id) {
        logger.info("Использован метод, deleteFaculty");
        facultyRepository.deleteById(id);
    }

    @Override
    public List<Faculty> sortFaculties(String color) {
        logger.info("Использован метод, sortFaculties");
        return facultyRepository.findAll().stream().filter((p) -> p.getColor().equals(color)).
                collect(Collectors.toList());
    }

    @Override
    public Collection<Faculty> findByNameOrColor(String name, String color) {
        logger.info("Использован метод, findByNameOrColor");
        return facultyRepository.findByNameOrColorAllIgnoreCase(name, color);
    }

    @Override
    public Collection<Student> findByName(String name) {
        logger.info("Использован метод, findByName");
        return facultyRepository.findByName(name).getStudents();
    }
    @Override
    public String longestFacultyName(){

        return facultyRepository.findAll()
                .stream()
                .map(Faculty::getName)
                .max( Comparator.comparing(String::length))
                .orElseThrow(() -> new NotFoundException("нет"));
    }

}

