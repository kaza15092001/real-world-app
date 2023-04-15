package com.khoingyen.realworldapp.model.user.mapper;

import com.khoingyen.realworldapp.entity.User;
import com.khoingyen.realworldapp.model.user.dto.UserDTOCreate;
import com.khoingyen.realworldapp.model.user.dto.UserDTOResponse;
import com.khoingyen.realworldapp.model.user.dto.UserDTOUpdate;

public class UserMapper {
    public static UserDTOResponse toUserDTOResponse(User user) {
        return UserDTOResponse.builder()
                .email(user.getEmail())
                .token(null)
                .username(user.getUsername())
                .bio(user.getBio())
                .image(user.getImage())
                .build();
    }

    public static User toUser(UserDTOCreate userDTOCreate) {
        return User.builder()
                .username(userDTOCreate.getUsername())
                .email(userDTOCreate.getEmail())
                .password(userDTOCreate.getPassword())
                .build();
    }

    public static User toUser(UserDTOUpdate userDTOUpdate, User userLoggedIn) {
        userLoggedIn.setBio(userDTOUpdate.getBio());
        userLoggedIn.setEmail(userDTOUpdate.getEmail());
        u
    }

}
