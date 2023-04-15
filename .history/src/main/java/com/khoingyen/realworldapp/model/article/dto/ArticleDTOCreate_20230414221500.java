package com.khoingyen.realworldapp.model.article.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ArticleDTOCreate {
    private String title;
    private String description;
    private String body;
    private List<String> tagList;
}
