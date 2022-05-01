package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.Interface.FacultyService;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final Map<Long, Faculty> faculties = new HashMap<>();
    private long lastID = 1;

    @Override
    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(lastID++);
        faculties.put(lastID, faculty);
        return faculty;
    }

    @Override
    public Faculty findFaculty(Long id) {
        return faculties.get(id);
    }

    @Override
    public Faculty editFaculty(Long lastID, Faculty faculty) {
        faculties.put(lastID, faculty);
        return faculty;
    }

    @Override
    public Faculty deleteFaculty(Long id) {
        return faculties.remove(id);
    }

    @Override
    public List<Faculty> sortFaculties(String color) {
        return faculties.values().stream().filter((p) -> p.getColor().equals(color)).collect(Collectors.toList());
    }
}

