package com.khoingyen.realworldapp.service.imlp;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.khoingyen.realworldapp.entity.Article;
import com.khoingyen.realworldapp.entity.User;
import com.khoingyen.realworldapp.model.article.dto.ArticleDTOCreate;
import com.khoingyen.realworldapp.model.article.dto.ArticleDTOResponse;
import com.khoingyen.realworldapp.model.article.mapper.ArticleMapper;
import com.khoingyen.realworldapp.repository.ArticleRepository;
import com.khoingyen.realworldapp.service.ArticleService;
import com.khoingyen.realworldapp.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final UserService userService;

    @Override
    public Map<String, ArticleDTOResponse> createArticle(Map<String, ArticleDTOCreate> articleDTOCreateMap) {
        ArticleDTOCreate articleDTOCreate = articleDTOCreateMap.get("article");

        Article article = ArticleMapper.toArticle(articleDTOCreate);
        User currentUser = userService.getUserLoggedIn();

        article.setAuthor(currentUser);

        article = articleRepository.save(a)

        return null;
    }

}
