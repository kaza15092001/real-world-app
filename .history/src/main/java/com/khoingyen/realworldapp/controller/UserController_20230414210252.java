package com.khoingyen.realworldapp.controller;

import com.khoingyen.realworldapp.exception.custom.CustomNotFoundException;
import com.khoingyen.realworldapp.model.user.dto.UserDTOCreate;
import com.khoingyen.realworldapp.model.user.dto.UserDTORequestLogin;
import com.khoingyen.realworldapp.model.user.dto.UserDTOResponse;
import com.khoingyen.realworldapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class UserController {
    private final UserService userService;
    @PostMapping("users/login")
    public Map<String, UserDTOResponse>
        login(@RequestBody Map<String, UserDTORequestLogin> userRequestLoginMap ) throws CustomNotFoundException {
        return userService.login(userRequestLoginMap);
    }

    @PostMapping("users")
    public Map<String, UserDTOResponse> register(@RequestBody Map<String, UserDTOCreate> userDTOCreateMap) {
        return userService.register(userDTOCreateMap);
    }

    @GetMapping("/user")
    public Map<String, UserDTOResponse> getCurrentUser() throws CustomNotFoundException {
        return userService.getCurrentUser();
    }

    @PutMapping("/user")
    public Map<String, UserDTOResponse> updateUser(@RequestBody Map) {
        return userService.updateUser();
    }
}
