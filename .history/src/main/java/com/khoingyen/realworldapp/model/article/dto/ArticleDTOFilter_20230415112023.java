package com.khoingyen.realworldapp.model.article.dto;


import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ArticleDTOFilter {
    private String tag;
    private String author;
    private String favorited;
    private int limit;
    private int offset;
}
