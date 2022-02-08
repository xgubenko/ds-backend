package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class TemplateController {

    @GetMapping("/user/info")
    public ResponseEntity<String> getLoginView() {
        return new ResponseEntity<>("{\n" +
                "    \"username\": \"user\",\n" +
                "    \"password\": \"pass\"\n" +
                "}", HttpStatus.OK);
    }

    @GetMapping("/courses")
    public String getCourses() {
        return "courses";
    }
}
