package com.example.mycrudproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyCrudProjectController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from MyCrudProjectController and first commit Feature";
    }
}
