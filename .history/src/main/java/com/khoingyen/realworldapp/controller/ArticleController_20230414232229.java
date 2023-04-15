package com.khoingyen.realworldapp.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khoingyen.realworldapp.model.article.dto.ArticleDTOCreate;
import com.khoingyen.realworldapp.model.article.dto.ArticleDTOResponse;
import com.khoingyen.realworldapp.service.ArticleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("")
    public Map<String, ArticleDTOResponse> createArticles(
            @RequestBody Map<String, ArticleDTOCreate> articleDTOCreateMap) {
        
    }
}
