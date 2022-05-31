package ru.hogwarts.school.controllers;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;


@RestController
public class InfoController {
    @Value("${server.port}")
    String value;

    @GetMapping("/getPort")
    public String getPort() {
        return value;
    }

    @GetMapping("/getNumber")
        public int getNumber(){
         int sum = Stream.iterate(1, a -> a +1) .limit(1_000_000) .reduce(0, (a, b) -> a + b );
         return sum;
        }
}
