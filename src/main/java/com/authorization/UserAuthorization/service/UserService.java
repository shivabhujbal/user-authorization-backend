package com.authorization.UserAuthorization.service;

import com.authorization.UserAuthorization.dao.request.SignUpRequest;
import com.authorization.UserAuthorization.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<com.authorization.UserAuthorization.entity.User> getUserById(int id);

    User updateUserDetails(int id, SignUpRequest signUpRequest);

    void deleteUser(int id);

    User findByUsername(String username);

    List<User> getAllUsers();
    UserDetailsService userDetailsService();
}
