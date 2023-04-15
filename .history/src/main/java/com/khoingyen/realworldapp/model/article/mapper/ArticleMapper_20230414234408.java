package com.khoingyen.realworldapp.model.article.mapper;

import com.khoingyen.realworldapp.entity.Article;
import com.khoingyen.realworldapp.model.article.dto.ArticleDTOCreate;

public class ArticleMapper {

    public static Article toArticle(ArticleDTOCreate articleDTOCreate) {
        Article article = Article.builder()
                .title(articleDTOCreate.getTitle())
                .description(articleDTOCreate.getDescription())
                .body(articleDTOCreate.getBody())
                .build();
        article.setTagList(null);
    }

}
