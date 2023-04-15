package com.khoingyen.realworldapp.service.imlp;

import com.khoingyen.realworldapp.entity.User;
import com.khoingyen.realworldapp.exception.custom.CustomNotFoundException;
import com.khoingyen.realworldapp.model.CustomError;
import com.khoingyen.realworldapp.model.profile.dto.ProfileDTOResponse;
import com.khoingyen.realworldapp.model.user.dto.UserDTOCreate;
import com.khoingyen.realworldapp.model.user.dto.UserDTORequestLogin;
import com.khoingyen.realworldapp.model.user.dto.UserDTOResponse;
import com.khoingyen.realworldapp.model.user.mapper.UserMapper;
import com.khoingyen.realworldapp.repository.UserRepository;
import com.khoingyen.realworldapp.service.UserService;
import com.khoingyen.realworldapp.util.JwtTokenUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;

    public Map<String, UserDTOResponse> buildDTOResponse(User user) {
        Map<String, UserDTOResponse> wrapper = new HashMap<>();
        UserDTOResponse userDTOResponse = UserMapper.toUserDTOResponse(user);
        userDTOResponse.setToken(jwtTokenUtil.generateToken(user, 24 * 3600));
        wrapper.put("user", userDTOResponse);
        return wrapper;
    }

    @Override
    public Map<String, UserDTOResponse> login(Map<String, UserDTORequestLogin> userRequestLoginMap) throws CustomNotFoundException {
        UserDTORequestLogin userDTORequestLogin = userRequestLoginMap.get("user");

        Optional<User> userOptional = userRepository.findByEmail(userDTORequestLogin.getEmail());
        boolean isAuthentication = false;
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(userDTORequestLogin.getPassword(), user.getPassword())) isAuthentication = true;
        }
        if (!isAuthentication) {
            throw new CustomNotFoundException(CustomError.builder().code("404").message("Email or Password is incorrect").build());
        }
        return buildDTOResponse(userOptional.get());
    }

    @Override
    public Map<String, UserDTOResponse> register(Map<String, UserDTOCreate> userDTOCreateMap) {
        UserDTOCreate userDTOCreate = userDTOCreateMap.get("user");
        User user = UserMapper.toUser(userDTOCreate);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return buildDTOResponse(user);
    }

    public User getUserLoggedIn() {
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principle instanceof UserDetails) {
            String email = ((UserDetails) principle).getUsername();
            User user = userRepository.findByEmail(email).get();
            return user;
        }
        return null;
    }

    @Override
    public Map<String, UserDTOResponse> getCurrentUser() throws CustomNotFoundException {
        User userLoggedIn = getUserLoggedIn();
        if (userLoggedIn != null) return buildDTOResponse(userLoggedIn);
        throw new CustomNotFoundException(CustomError.builder().code("404").message("User not exist").build());
    }

    @Override
    public Map<String, ProfileDTOResponse> getProfile(String username) throws CustomNotFoundException {
        User userLoggedIn = getUserLoggedIn();

        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new CustomNotFoundException(CustomError.builder().code("404").message("User not found").build());
        }

        User user = userOptional.get();
        Set<User> followers = user.getFollowers();
        boolean isFollowing = false;
        for (User u: followers) {
            if(u.getId() == userLoggedIn.getId()) {
                isFollowing = true;
                break;
            }
        }

        return buildProfileResponse(userOptional.get(), isFollowing);
    }

    private Map<String, ProfileDTOResponse> buildProfileResponse(User user, boolean isFollowing) {
        Map<String, ProfileDTOResponse> wrapper = new HashMap<>();
        ProfileDTOResponse profileDTOResponse = ProfileDTOResponse.builder()
                .username(user.getUsername())
                .bio(user.getBio())
                .image(user.getImage())
                .following(isFollowing)
                .build();
        wrapper.put("profile", profileDTOResponse);
        return wrapper;
    }

    @Override
    public Map<String, ProfileDTOResponse> followUser(String username) throws CustomNotFoundException {
        User userLoggedIn = getUserLoggedIn();

        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new CustomNotFoundException(CustomError.builder().code("404").message("User not found").build());
        }

        User user = userOptional.get();
        Set<User> followers = user.getFollowers();
        boolean isFollowing = false;
        for (User u: followers) {
            if(u.getId() == userLoggedIn.getId()) {
                isFollowing = true;
                break;
            }
        }

        if(!isFollowing) {
            isFollowing = true;
            user.getFollowers().add(userLoggedIn);
            user = userRepository.save(user);
            isFollowing = true;
        }

        return buildProfileResponse(userOptional.get(), isFollowing);
    }

    @Override
    public Map<String, ProfileDTOResponse> unfollowUser(String username) throws CustomNotFoundException {
        User userLoggedIn = getUserLoggedIn();

        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new CustomNotFoundException(CustomError.builder().code("404").message("User not found").build());
        }

        User user = userOptional.get();
        Set<User> followers = user.getFollowers();
        boolean isFollowing = false;
        for (User u: followers) {
            if(u.getId() == userLoggedIn.getId()) {
                isFollowing = true;
                break;
            }
        }

        if(isFollowing) {
            isFollowing = false;
            user.getFollowers().remove(userLoggedIn);
            user = userRepository.save(user);
            isFollowing = false;
        }

        return buildProfileResponse(userOptional.get(), isFollowing);
    }
}
