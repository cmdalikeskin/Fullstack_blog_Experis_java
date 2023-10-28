package org.example.controller;


import org.example.model.Blog;
import org.example.repository.AuthorRepository;
import org.example.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private final AuthorRepository authorRepository;


    public AuthorController(AuthorRepository repository) {

        this.authorRepository = repository;
    }





}
