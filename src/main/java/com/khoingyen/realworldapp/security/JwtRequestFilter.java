package com.khoingyen.realworldapp.security;

import com.khoingyen.realworldapp.entity.User;
import com.khoingyen.realworldapp.model.TokenPayLoad;
import com.khoingyen.realworldapp.repository.UserRepository;
import com.khoingyen.realworldapp.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");

        String token = null;
        TokenPayLoad tokenPayLoad = null;
        if(requestTokenHeader != null && requestTokenHeader.startsWith("Token")) {
            token = requestTokenHeader.substring(6);

            try {
                tokenPayLoad = jwtTokenUtil.getTokenPayLoad(token);
            }
            catch (SignatureException ex) {
                System.out.println("Invalid jwt signature");
            }
            catch (IllegalArgumentException ex) {
                System.out.println("Unable to get jwt");
            }
            catch (ExpiredJwtException ex) {
                System.out.println("Token has expired");
            }
        }
        else {
            System.out.println("Jwt token does not start with token");
        }

        //not yet login -> set authentication
        if(tokenPayLoad != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<User> userOptional = userRepository.findById(tokenPayLoad.getUserId());
            if(userOptional.isPresent()) {
                User user = userOptional.get();
                if(jwtTokenUtil.validate(token, user)) {
                    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
;
                    UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(),
                            user.getPassword(), authorities);

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                            = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        System.out.println(tokenPayLoad);
        filterChain.doFilter(request, response);
    }
}
