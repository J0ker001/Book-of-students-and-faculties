package ru.hogwarts.school.controllers;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import ru.hogwarts.school.Interface.AvatarService;
import ru.hogwarts.school.Interface.FacultyService;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.AvatarRepository;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Faculty facultyMock;

    @MockBean
    private FacultyRepository facultyRepository;

    @MockBean
    private AvatarRepository avatarRepository;

    @MockBean
    private FacultyService facultyService;

    @MockBean
    private AvatarService avatarService;

    @MockBean
    private AvatarController avatarController;

    @MockBean
    private StudentController studentController;

    @InjectMocks
    private FacultyController facultyController;


    @Test
    void creatFaculty() throws Exception {
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("FacOne", "colorOne");

        Faculty faculty = new Faculty(1L, "FacOne", "colorOne");

        when(facultyService.createFaculty(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty/createNew")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("FacOne"))
                .andExpect(jsonPath("$.color").value("colorOne"));
    }

    @Test
    void getFaculty() throws Exception {
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("FacOne", "colorOne");

        Faculty faculty = new Faculty(1L, "FacOne", "colorOne");

        when(facultyService.findFaculty(any(Long.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/find/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("FacOne"))
                .andExpect(jsonPath("$.color").value("colorOne"));
    }

    @Test
    void editFaculty() throws Exception {
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("FacOne", "colorOne");

        Faculty faculty = new Faculty(1L, "FacOne", "colorOne");

        when(facultyService.editFaculty(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty/update", faculty.getId(), faculty)
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("FacOne"))
                .andExpect(jsonPath("$.color").value("colorOne"));
    }

    @Test
    void deleteFaculty() throws Exception {
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("FacOne", "colorOne");
        Faculty faculty = new Faculty(1L, "FacOne", "colorOne");
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/delete/1", faculty.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void filteredColor() throws Exception {
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("FacOne", "colorOne");

        Faculty faculty = new Faculty(1L, "FacOne", "colorOne");
        List<Faculty> list = List.of(faculty);

        when(facultyService.sortFaculties(any(String.class))).thenReturn(list);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/filter/color")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("FacOne"))
                .andExpect(jsonPath("$[0].color").value("colorOne"));
    }

    @Test
    void findByNameOrColor() throws Exception {

        Faculty facultyOne = new Faculty(1L, "Гуманитарный", "Зеленый");
        Faculty facultyTwo = new Faculty(2L, "Экономикий", "Зеленый");

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("Гуманитарный", facultyOne);
        facultyObject.put("Экономикий", facultyTwo);

        List<Faculty> facultiesOne = List.of(facultyOne, facultyTwo);
        List<Faculty> facultiesTwo = List.of(facultyOne);

        when(facultyService.findByNameOrColor(any(String.class), any(String.class))).thenReturn(facultiesOne);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/findNAmeORColor?name=Зеленый&color=Зеленый")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Гуманитарный"))
                .andExpect(jsonPath("$[0].color").value("Зеленый"))
                .andExpect(jsonPath("$[1].name").value("Экономикий"))
                .andExpect(jsonPath("$[1].color").value("Зеленый"));

        when(facultyService.findByNameOrColor(any(String.class), any(String.class))).thenReturn(facultiesTwo);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/findNAmeORColor?name=Гуманитарный&color=Гуманитарный")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Гуманитарный"))
                .andExpect(jsonPath("$[0].color").value("Зеленый"))
                .andExpect(jsonPath("$[0].id").value("1"));
    }

    @Test
    void findByName() throws Exception {

        Student student = new Student(1L, "1", 2);
        List<Student> students = List.of(student);
        Faculty faculty = new Faculty(1L, "N1", "color", students);

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("N1", faculty);

        when(facultyService.findByName(any(String.class))).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/search?name=N1")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}