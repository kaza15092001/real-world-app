package com.khoingyen.realworldapp.repository.custom;

import javax.persistence.EntityManager;

import org.hibernate.mapping.Map;
import org.springframework.stereotype.Repository;

import com.khoingyen.realworldapp.model.article.dto.ArticleDTOFilter;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArticleCriteria {
    private final EntityManager em;

    public void findAll(ArticleDTOFilter filter) {
        StringBuilder query = new StringBuilder("select a from Article a where 1=1");
        if(filter.getTag() != null) {
            
        }
    }
}
