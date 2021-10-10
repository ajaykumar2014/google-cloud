package com.javatech.spring.cloud.sql.api.controllers;

import com.javatech.spring.cloud.sql.api.repo.DatabaseRepository;
import com.javatech.spring.cloud.sql.api.repo.models.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorsController {

    @Autowired
    private DatabaseRepository databaseRepository;

    @GetMapping
    public ResponseEntity<?> test(){
        return ResponseEntity.ok().body("OK");
    }

    @GetMapping(value = "/authors", produces = MediaType.APPLICATION_JSON_VALUE
            )
    public List<Author> getAuthors(){
        System.out.println("databaseRepository.findAll()"+databaseRepository.findAll());
        return databaseRepository.findAll();
    }
}
