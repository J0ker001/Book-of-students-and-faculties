package ru.hogwarts.school.Interface;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;


import java.util.Collection;
import java.util.List;

public interface FacultyService {

    Faculty createFaculty(Faculty faculty);

    Faculty findFaculty(Long id);

    Faculty editFaculty(Faculty faculty);

    void deleteFaculty(Long id);

    List<Faculty> sortFaculties(String color);

    Collection<Faculty> findByNameOrColor(String name, String color);

    Collection<Student> findByName(String name);

}
