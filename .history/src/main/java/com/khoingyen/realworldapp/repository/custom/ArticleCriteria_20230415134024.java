package com.khoingyen.realworldapp.repository.custom;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.khoingyen.realworldapp.entity.Article;
import com.khoingyen.realworldapp.model.article.dto.ArticleDTOFilter;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArticleCriteria {
    private final EntityManager em;

    public void findAll(ArticleDTOFilter filter) {
        StringBuilder query = new StringBuilder(
                "select a from Article a inner join a.author au inner join a.userFavorited ufa where 1=1");
        Map<String, Object> params = new HashMap<>();
        if (filter.getTag() != null) {
            query.append(" and a.tagList like :tag");
            params.put("tag", "%" + filter.getTag() + "%");
        }
        if (filter.getAuthor() != null) {
            query.append(" and au.username = :author");
            params.put("author", "%" + filter.getAuthor() + "%");
        }
        if (filter.getFavorited() != null) {
            query.append(" and ufa.username = :favorited");
            params.put("favorited", "%" + filter.getFavorited() + "%");
        }

        String countQueryStr = query.toString().replace("select a", "select count(a.id)");

        TypedQuery<Article> tQuery = em.createQuery(query.toString(), Article.class);
        Query countQuery = em.createQuery(countQueryStr);

        params.forEach((k, v) -> {
            tQuery.setParameter(k, v);
            countQuery.setParameter(k, v);
        });

        tQuery.setFirstResult(filter.getOffset());
        tQuery.setMaxResults(filter.getLimit());
        long totalArticle = ()
    }
}
