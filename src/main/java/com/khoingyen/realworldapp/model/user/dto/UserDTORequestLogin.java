package com.khoingyen.realworldapp.model.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTORequestLogin {
    private String email;
    private String password;
}
