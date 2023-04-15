package com.khoingyen.realworldapp.model.article.mapper;

import com.khoingyen.realworldapp.entity.Article;
import com.khoingyen.realworldapp.model.article.dto.ArticleDTOCreate;
import com.khoingyen.realworldapp.util.SlugUtil;

public class ArticleMapper {

    public static Article toArticle(ArticleDTOCreate articleDTOCreate) {
        Article article = Article.builder()
                .title(articleDTOCreate.getTitle())
                .description(articleDTOCreate.getDescription())
                .body(articleDTOCreate.getBody())
                .slug(SlugUtil.getSlug(articleDTOCreate.g))
                .build();
        article.setTagList(articleDTOCreate.getTagList());
        return article;
    }

}
