package com.khoingyen.realworldapp.service.imlp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.khoingyen.realworldapp.entity.Article;
import com.khoingyen.realworldapp.entity.User;
import com.khoingyen.realworldapp.model.article.dto.ArticleDTOCreate;
import com.khoingyen.realworldapp.model.article.dto.ArticleDTOFilter;
import com.khoingyen.realworldapp.model.article.dto.ArticleDTOResponse;
import com.khoingyen.realworldapp.model.article.mapper.ArticleMapper;
import com.khoingyen.realworldapp.repository.ArticleRepository;
import com.khoingyen.realworldapp.repository.custom.ArticleCriteria;
import com.khoingyen.realworldapp.service.ArticleService;
import com.khoingyen.realworldapp.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final UserService userService;
    private final ArticleCriteria articleCriteria;

    @Override
    public Map<String, ArticleDTOResponse> createArticle(Map<String, ArticleDTOCreate> articleDTOCreateMap) {
        ArticleDTOCreate articleDTOCreate = articleDTOCreateMap.get("article");

        Article article = ArticleMapper.toArticle(articleDTOCreate);
        User currentUser = userService.getUserLoggedIn();
        article.setAuthor(currentUser);

        article = articleRepository.save(article);
        Map<String, ArticleDTOResponse> wrapper = new HashMap<>();
        ArticleDTOResponse articleDTOResponse = ArticleMapper
                .toArticleDTOResponse(article, false, 0, getFollowAuthor(article));
        wrapper.put("article", articleDTOResponse);
        return wrapper;
    }

    public boolean getFollowAuthor(Article article) {
        User userLoggedIn = userService.getUserLoggedIn();
        User author = article.getAuthor();
        Set<User> followers = author.getFollowers();
        boolean isFollowing = false;
        for (User u : followers) {
            if (u.getId() == userLoggedIn.getId()) {
                isFollowing = true;
                break;
            }
        }
        return isFollowing;
    }

    @Override
    public Map<String, ArticleDTOResponse> getArticleBySlug(String slug) {
        Article article = articleRepository.findBySlug(slug);

        Map<String, ArticleDTOResponse> wrapper = new HashMap<>();

        boolean isFollowing = getFollowAuthor(article);

        ArticleDTOResponse articleDTOResponse = ArticleMapper
                .toArticleDTOResponse(article, false, 0, isFollowing);
        wrapper.put("article", articleDTOResponse);
        return wrapper;
    }

    @Override
    public Map<String, Object> getListArticles(ArticleDTOFilter filter) {

        Map<String, Object> results = articleCriteria.findAll(filter);
        List<Article> listArticles = (List<Article>) results.get("listArticles");
        Long totalArticle = (long) results.get("totalArticle");

        List<ArticleDTOResponse> listArticleDTOResponses = listArticles.stream()
                .map(article -> ArticleMapper.toArticleDTOResponse(article, false, 0, false))
                .collect(Collectors.toList());
        Map<String, Object> wrapper = new HashMap<>();
        wrapper.put("articles", listArticleDTOResponses);
        wrapper.put("articleCount", totalArticle);
        return null;
    }
}
