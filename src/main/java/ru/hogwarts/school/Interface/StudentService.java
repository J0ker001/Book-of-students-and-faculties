package ru.hogwarts.school.Interface;

import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentService {

    Student createStudent(Student student);

    Student findStudent(long id);

    Student editStudent(Student student);

    Student deleteStudent(Long id);

    List<Student> sortStudent(Integer age);
}
