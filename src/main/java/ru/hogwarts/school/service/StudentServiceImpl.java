package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.Interface.StudentService;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements StudentService {

    private final Map<Long, Student> students = new HashMap<>();
    private long lastID = 1;

    @Override
    public Student createStudent(Student student) {
        student.setId(lastID++);
        students.put(lastID, student);
        return student;
    }

    @Override
    public Student findStudent(long id) {
        return students.get(id);
    }

    @Override
    public Student editStudent(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    @Override
    public Student deleteStudent(Long id) {
        return students.remove(id);
    }

    @Override
    public List<Student> sortStudent(Integer age) {
        return students.values().stream().filter((p) -> p.getAge() == age).collect(Collectors.toList());
    }
}
