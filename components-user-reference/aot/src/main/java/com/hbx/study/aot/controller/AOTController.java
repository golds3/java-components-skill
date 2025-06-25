package com.hbx.study.aot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AOTController {
    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
