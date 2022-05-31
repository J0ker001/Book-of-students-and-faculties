package ru.hogwarts.school.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAgeBetween(Integer a, Integer b);

    Student findByName(String name);

    @Query(value = "SELECT COUNT(name) FROM student ", nativeQuery = true)
    Integer amountStudents();

    @Query(value = "SELECT AVG(age)  FROM student ", nativeQuery = true)
    Integer avgAge();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    Collection<Student> lastFiveStudents();

}
