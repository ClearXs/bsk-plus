package com.bsk.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiControllerTest {

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
