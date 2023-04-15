package com.khoingyen.realworldapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khoingyen.realworldapp.service.ArticleService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping(value="path")
    public SomeEnityData postMethodName(@RequestBody SomeEnityData entity) {
        //TODO: process POST request
        
        return entity;
    }
    
}
