package com.khoingyen.realworldapp.model.article.dto;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ArticleDTOResponse {
    private String slug;
    private String title;
    private String description;
    private String body;
    private List<String> tagList;
    private Date created
}
