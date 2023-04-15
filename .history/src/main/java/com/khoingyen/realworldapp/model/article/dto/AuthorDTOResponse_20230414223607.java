package com.khoingyen.realworldapp.model.article.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AuthorDTOResponse {
    private String username;
    private String bio;
    private String image;
    private boolean    
}
