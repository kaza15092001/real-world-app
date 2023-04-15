package com.khoingyen.realworldapp.model.article.mapper;

import com.khoingyen.realworldapp.entity.Article;
import com.khoingyen.realworldapp.model.article.dto.ArticleDTOCreate;

public class ArticleMapper {

    public static Article toArticle(ArticleDTOCreate articleDTOCreate) {
        return Article.builder()
        .title(articleDTOCreate.getTitle())
        .description(articleDTOCreate.g)
        .build();
    }
    
}
