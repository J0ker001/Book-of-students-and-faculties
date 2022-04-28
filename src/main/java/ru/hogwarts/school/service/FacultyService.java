package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final Map<Long, Faculty> faculties = new HashMap<>();
    private long lastID = 0;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(lastID++);
        faculties.put(lastID, faculty);
        return faculty;
    }

    public Faculty findFaculty(Long id) {
        return faculties.get(id);
    }

    public Faculty editFaculty(Long lastID, Faculty faculty) {
        faculties.put(lastID, faculty);
        return faculty;
    }

    public Faculty deleteFaculty(Long id) {
        --lastID;
        return faculties.remove(id);

    }

    public List<Map.Entry<Long, Faculty>> sortFaculties(String color) {
        return faculties.entrySet().stream().filter((p) -> p.getValue().
                getColor().equals(color)).collect(Collectors.toList());
    }
}

