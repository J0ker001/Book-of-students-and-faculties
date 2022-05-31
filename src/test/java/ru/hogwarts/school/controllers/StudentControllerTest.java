package ru.hogwarts.school.controllers;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;


import java.util.Collection;
import java.util.Objects;


import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
        assertThat(studentController).isNotNull();
    }

    @Test
    void creatStudent() {
        Student student = new Student(null, "Test", 27);
        studentController.creatStudent(student);
        Long id = student.getId();
        ResponseEntity<Student> response =
                restTemplate.postForEntity("http://localhost:" + port + "/student/creatNew",
                        student, Student.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody()).getName()).isEqualTo("Test");
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getAge()).isEqualTo(27);
        studentController.deleteStudent(student.getId());
    }

    @Test
    void getStudent() {
        Student student = new Student(null, "Test", 27);
        Long id = Objects.requireNonNull(studentController.creatStudent(student).getBody()).getId();
        ResponseEntity<Student> response =
                restTemplate.getForEntity("http://localhost:" + port + "/student/{id}", Student.class, id);
        assert response != null;
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody()).getName()).isEqualTo("Test");
        assertThat(response.getBody().getAge()).isEqualTo(27);
        studentController.deleteStudent(id);
    }

    @Test
    void updateStudent() {
        Student student = new Student(null, "Test", 27);
        studentController.creatStudent(student);
        Long id = student.getId();
        Student studentTest = new Student(id, "TestPut", 28);
        restTemplate.put("http://localhost:" + port + "/student/update", studentTest, id);
        ResponseEntity<Student> response =
                restTemplate.getForEntity("http://localhost:" + port + "/student/{id}", Student.class, id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody()).getName()).isEqualTo("TestPut");
        assertThat(response.getBody().getAge()).isEqualTo(28);
        studentController.deleteStudent(id);
    }

    @Test
    void deleteStudent() {
        Student student = new Student(null, "Test", 27);
        studentController.creatStudent(student);
        Long id = Objects.requireNonNull(studentController.creatStudent(student).getBody()).getId();
        restTemplate.delete("http://localhost:" + port + "/student/delete{id}", id, Student.class);
        ResponseEntity<Student> response =
                restTemplate.getForEntity("http://localhost:" + port + "/student/{id}", Student.class, id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Test
    void filteredStudent() {
        Student student = new Student(null, "Test", 27);
        Integer ageFalse = 175;
        Integer age = Objects.requireNonNull(studentController.creatStudent(student).getBody()).getAge();
        var responseFalse = studentController.filteredStudent(ageFalse);
        Collection<Student> response =
                restTemplate.getForObject("http://localhost:" + port + "/student/filter{age}?age=0",
                        Collection.class, age);
        assertThat(response.size()).isNotNull();
        assertThat(responseFalse.size()).isZero();
        studentController.deleteStudent(student.getId());
    }

    @Test
    void findByAgeBetween() {
        Student student = new Student(null, "Test", 70);
        Student studentTwo = new Student(null, "Test2", 71);
        studentController.creatStudent(student);
        studentController.creatStudent(studentTwo);
        Collection<Student> response =
                restTemplate.getForObject("http://localhost:" + port + "/student/between?minAge=70&maxAge=71",
                        Collection.class);
        assertThat(response.size()).isEqualTo(2);
        studentController.deleteStudent(student.getId());
        studentController.deleteStudent(studentTwo.getId());
    }

    @Test
    void findByFacultyId() {
        Student student = new Student(null, "Test", 27,
                new Faculty(3L, "Юридический", "Синий"));
        studentController.creatStudent(student);
        String name = student.getName();
        Long id = student.getId();
        Student response =
                restTemplate.getForObject("http://localhost:" + port + "/student/facult/name?name=Test",
                        Student.class, name);
        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getName()).isEqualTo(name);
        assertThat(response.getFaculty()).isEqualTo(new Faculty(3L, "Юридический", "Синий"));
        studentController.deleteStudent(id);
    }
}