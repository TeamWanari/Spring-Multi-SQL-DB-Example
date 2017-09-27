package com.wanari.multidb.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping(
        value = "",
        method = RequestMethod.GET)
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hai!");
    }
}
