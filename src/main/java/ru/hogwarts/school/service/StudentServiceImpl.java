package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.hogwarts.school.Interface.StudentService;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;


@Service("student")
public class StudentServiceImpl implements StudentService {

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    private final StudentRepository studentRepository;
    private final Object synchr = new Object();

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


    @Override
    public List<String> getAllStudentStartASort() {
        return studentRepository.findAll()
                .stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("А"))
                .sorted()
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public double getAvgAgeStudents() {
        return studentRepository.findAll()
                .stream()
                .mapToDouble(Student::getAge)
                .average()
                .orElseThrow(() -> new NotFoundException("Нет студентов"));
    }

    @Override
    public void parallelFlow() {

        List<String> names = studentRepository.findAll().stream().map(Student::getName).collect(Collectors.toList());

        System.out.println(names.get(0));
        System.out.println(names.get(1));

        new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName);
            System.out.println(names.get(2));
            System.out.println(names.get(3));
        }).start();

        new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName);
            System.out.println(names.get(4));
            System.out.println(names.get(5));
        }).start();
    }


    @Override
    public void parallelFlowSynchronized() {

        List<String> names = studentRepository.findAll().stream().map(Student::getName).collect(Collectors.toList());

        System.out.println(names.get(0));
        System.out.println(names.get(1));
        new Thread(() -> {
            synchronized (synchr) {
                System.out.println(names.get(2));
                System.out.println(names.get(3));
            }
        }).start();
        new Thread(() -> {
            synchronized (synchr) {
                System.out.println(names.get(4));
                System.out.println(names.get(5));
            }
        }).start();
    }

}


