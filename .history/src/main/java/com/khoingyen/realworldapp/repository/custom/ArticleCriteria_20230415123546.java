package com.khoingyen.realworldapp.repository.custom;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.khoingyen.realworldapp.model.article.dto.ArticleDTOFilter;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArticleCriteria {
    private final EntityManager em;

    public void findAll(ArticleDTOFilter filter) {
        StringBuilder query = new StringBuilder("select a from Article a where 1=1");
        Map<String, Object> params = new HashMap<>();
        if(filter.getTag() != null) {
            query.append(" and a.tagList like :tag");
            params.put("tag", "%"+filter.getTag()+"%");
        }
        if(f)
    }
}
