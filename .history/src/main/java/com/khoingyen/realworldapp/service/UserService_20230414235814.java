package com.khoingyen.realworldapp.service;

import com.khoingyen.realworldapp.entity.User;
import com.khoingyen.realworldapp.exception.custom.CustomNotFoundException;
import com.khoingyen.realworldapp.model.profile.dto.ProfileDTOResponse;
import com.khoingyen.realworldapp.model.user.dto.UserDTOCreate;
import com.khoingyen.realworldapp.model.user.dto.UserDTORequestLogin;
import com.khoingyen.realworldapp.model.user.dto.UserDTOResponse;
import com.khoingyen.realworldapp.model.user.dto.UserDTOUpdate;

import java.util.Map;

public interface UserService {
    public Map<String, UserDTOResponse>
        login(Map<String, UserDTORequestLogin> userRequestLoginMap) throws CustomNotFoundException;

    public Map<String, UserDTOResponse> register(Map<String, UserDTOCreate> userDTOCreateMap);

    public Map<String, UserDTOResponse> getCurrentUser() throws CustomNotFoundException;

    public Map<String, ProfileDTOResponse> getProfile(String username) throws CustomNotFoundException;

    public Map<String, ProfileDTOResponse> followUser(String username) throws CustomNotFoundException;

    public Map<String, ProfileDTOResponse> unfollowUser(String username) throws CustomNotFoundException;

    public Map<String, UserDTOResponse> updateUser(Map<String, UserDTOUpdate> userDTOUpdateMap);

    public User getUserLoggedIn()
}
