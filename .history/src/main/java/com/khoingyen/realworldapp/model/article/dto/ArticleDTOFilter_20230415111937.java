package com.khoingyen.realworldapp.model.article.dto;


import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ArticleDTOFilter {
    private String title;
    private String description;
    private String body;
    private List<String> tagList;
}
