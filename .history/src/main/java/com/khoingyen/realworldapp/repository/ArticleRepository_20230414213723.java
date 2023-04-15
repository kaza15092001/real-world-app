package com.khoingyen.realworldapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khoingyen.realworldapp.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, In {
    
}
