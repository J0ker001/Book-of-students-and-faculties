package ru.hogwarts.school.controllers;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class InfoController {
    @Value("${server.port}")
    String value;

    @GetMapping("/getPort")
    public String getPort() {
        return value;
    }
}
