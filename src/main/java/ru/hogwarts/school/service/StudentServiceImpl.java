package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.Interface.StudentService;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service("student")
public class StudentServiceImpl implements StudentService {


    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student findStudent(long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> sortStudent(Integer age) {
        return studentRepository.findAll().stream().filter((p) -> p.getAge() == age).collect(Collectors.toList());
    }

    @Override
    public Collection<Student> findByAgeBetween(Integer minAge, Integer maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    @Override
    public Student findByName(String name) {
        return studentRepository.findByName(name);
    }

    @Override
    public Integer amountStudents() {
        return studentRepository.amountStudents();
    }

    @Override
    public Integer avgAge() {
        return studentRepository.avgAge();
    }

    @Override
    public Collection<Student> lastFiveStudents() {
        return studentRepository.lastFiveStudents();
    }
}


