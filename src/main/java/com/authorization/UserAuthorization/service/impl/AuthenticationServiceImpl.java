package com.authorization.UserAuthorization.service.impl;

import com.authorization.UserAuthorization.dao.request.SignInRequest;
import com.authorization.UserAuthorization.dao.request.SignUpRequest;
import com.authorization.UserAuthorization.dao.response.AuthorizationResponse;
import com.authorization.UserAuthorization.entity.User;
import com.authorization.UserAuthorization.entity.UserRole;
import com.authorization.UserAuthorization.repository.UserRepository;
import com.authorization.UserAuthorization.service.AuthenticationService;
import com.authorization.UserAuthorization.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenUtil jwtTokenUtil;


    @Override
    public AuthorizationResponse signIn(SignInRequest request) {


        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("Incorrect Username or Password")
        );

        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new IllegalArgumentException("Incorrect Username or Password");
        }

        String jwt =jwtTokenUtil.generateToken(user);

        return AuthorizationResponse.builder().username(user.getUsername()).email(user.getEmail()).role(String.valueOf(user.getRole())).id(user.getId()).token(jwt).build();

    }

    @Override
    public User signUp(SignUpRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(UserRole.valueOf(request.getRole()))
                .build();
        userRepository.save(user);

        return user;
    }
}

