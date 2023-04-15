package com.khoingyen.realworldapp.service.imlp;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.khoingyen.realworldapp.model.article.dto.ArticleDTOCreate;
import com.khoingyen.realworldapp.model.article.dto.ArticleDTOResponse;
import com.khoingyen.realworldapp.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService{

    @Override
    public Map<String, ArticleDTOResponse> createArticle(Map<String, ArticleDTOCreate> articleDTOCreateMap) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createArticle'");
    }
    
}
