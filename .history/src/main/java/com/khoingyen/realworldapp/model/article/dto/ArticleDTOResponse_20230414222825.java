package com.khoingyen.realworldapp.model.article.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ArticleDTOResponse {
    private String slug;
    private String title;
    private String descr
}
