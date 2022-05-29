package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.Interface.StudentService;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service("student")
public class StudentServiceImpl implements StudentService {

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);


    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        logger.info("Вызван метод, createStudent");
        return studentRepository.save(student);
    }

    @Override
    public Student findStudent(long id) {
        logger.info("Вызван метод, findStudent");
        return studentRepository.findById(id).get();
    }

    @Override
    public Student editStudent(Student student) {
        logger.info("Вызван метод, editStudent");
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        logger.info("Вызван метод, deleteStudent");
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> sortStudent(Integer age) {
        logger.info("Вызван метод, sortStudent");
        return studentRepository.findAll().stream().filter((p) -> p.getAge() == age).collect(Collectors.toList());
    }

    @Override
    public Collection<Student> findByAgeBetween(Integer minAge, Integer maxAge) {
        logger.info("Вызван метод, findByAgeBetween");
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    @Override
    public Student findByName(String name) {
        logger.info("Вызван метод, findByName");
        return studentRepository.findByName(name);
    }

    @Override
    public Integer amountStudents() {
        logger.info("Вызван метод, amountStudents");
        return studentRepository.amountStudents();
    }

    @Override
    public Integer avgAge() {
        logger.info("Вызван метод, avgAge");
        return studentRepository.avgAge();
    }

    @Override
    public Collection<Student> lastFiveStudents() {
        logger.info("Вызван метод, lastFiveStudents");
        return studentRepository.lastFiveStudents();
    }
}


