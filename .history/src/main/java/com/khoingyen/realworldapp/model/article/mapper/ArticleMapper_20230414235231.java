package com.khoingyen.realworldapp.model.article.mapper;

import java.util.Date;

import com.khoingyen.realworldapp.entity.Article;
import com.khoingyen.realworldapp.model.article.dto.ArticleDTOCreate;
import com.khoingyen.realworldapp.util.SlugUtil;

public class ArticleMapper {

    public static Article toArticle(ArticleDTOCreate articleDTOCreate) {
        Date now = new Date();
        Article article = Article.builder()
                .title(articleDTOCreate.getTitle())
                .description(articleDTOCreate.getDescription())
                .body(articleDTOCreate.getBody())
                .slug(SlugUtil.getSlug(articleDTOCreate.getTitle()))
                .createdAt(now)
                ,
                .build();
        article.setTagList(articleDTOCreate.getTagList());
        return article;
    }

}
