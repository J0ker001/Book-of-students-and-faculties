package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class StudentService {

    private final Map<Long, Student> students = new HashMap<>();
    private long lastID = 0;

    public Student createStudent(Student student) {
        student.setId(++lastID);
        students.put(lastID, student);
        return student;
    }

    public Student findStudent(long id) {
        return students.get(id);
    }

    public Student editStudent(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    public Student deleteStudent(Long id) {
        --lastID;
        return students.remove(id);

    }

    public List<Map.Entry<Long, Student>> sortStudent(Integer age) {
        return students.entrySet().stream().filter((p) -> p.getValue().
                getAge() == age).collect(Collectors.toList());
    }
}
