package com.khoingyen.realworldapp.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Map<String, ArticleDTOResponse> createArticle(
            @RequestBody Map<String, ArticleDTOCreate> articleDTOCreateMap) {
        return articleService.createArticle(articleDTOCreateMap);
    }

    @GetMapping("/{slug}")
    public Map<String, ArticleDTOResponse> getArticleBySlug(@PathVariable String slug) {
        return articleService.getArticleBySlug(slug);
    }

    @GetMapping("")
    public Map<String, Object> getListArticles(
            @RequestParam(name = "tag", required = false) String tag,
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "favorited", required = false) String favorited,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset) {

                
        return articleService.getListArticles();
    }
}
