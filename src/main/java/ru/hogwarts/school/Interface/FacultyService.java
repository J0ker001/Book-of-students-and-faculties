package ru.hogwarts.school.Interface;

import ru.hogwarts.school.model.Faculty;

import java.util.List;

public interface FacultyService {

    Faculty createFaculty(Faculty faculty);

    Faculty findFaculty(Long id);

    Faculty editFaculty(Long lastID, Faculty faculty);

    Faculty deleteFaculty(Long id);

    List<Faculty> sortFaculties(String color);
}
