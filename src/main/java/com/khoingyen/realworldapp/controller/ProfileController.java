package com.khoingyen.realworldapp.controller;

import com.khoingyen.realworldapp.exception.custom.CustomNotFoundException;
import com.khoingyen.realworldapp.model.profile.dto.ProfileDTOResponse;
import com.khoingyen.realworldapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profiles/{username}")
public class ProfileController {
    private final UserService userService;

    @GetMapping("")
    public Map<String, ProfileDTOResponse> getProfile(@PathVariable String username) throws CustomNotFoundException {
        return userService.getProfile(username);
    }

    @PostMapping("/follow")
    public Map<String, ProfileDTOResponse> followUser(@PathVariable String username) throws CustomNotFoundException {
        return userService.followUser(username);
    }

    @DeleteMapping("/unfollow")
    public Map<String, ProfileDTOResponse> unfollowUser(@PathVariable String username) throws CustomNotFoundException {
        return userService.unfollowUser(username);
    }
}
