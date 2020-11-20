package com.ba.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hi")
public class HelloController {

    @GetMapping("/user")
    public String sayHelloForUser() {
        return "Hi from controller as USER";
    }

    @GetMapping("/admin")
    public String sayHelloForAdmin() {
        return "Hi from controller as ADMIN";
    }

}
