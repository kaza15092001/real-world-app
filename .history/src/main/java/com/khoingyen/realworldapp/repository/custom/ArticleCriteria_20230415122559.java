package com.khoingyen.realworldapp.repository.custom;

import javax.persistence.EntityManager;

import org.hibernate.mapping.Map;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArticleCriteria {
    private final EntityManager em;

    public Map
}
