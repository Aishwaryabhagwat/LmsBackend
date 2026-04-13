package com.cdac.E_Learning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RestController
@RequestMapping("/api")
public class TestController {
	@GetMapping("/helloo")
    public String hello() {
        return "Hello, Aishwarya!!";
    }
}
