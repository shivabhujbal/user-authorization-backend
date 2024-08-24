package com.authorization.UserAuthorization.service.impl;

import com.authorization.UserAuthorization.dao.request.SignUpRequest;
import com.authorization.UserAuthorization.entity.User;
import com.authorization.UserAuthorization.entity.UserRole;
import com.authorization.UserAuthorization.repository.UserRepository;
import com.authorization.UserAuthorization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService,UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not found"));
    }

    @Override
    public Optional<com.authorization.UserAuthorization.entity.User> getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUserDetails(int id, SignUpRequest signUpRequest) {
        Optional<com.authorization.UserAuthorization.entity.User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            com.authorization.UserAuthorization.entity.User existingUser = existingUserOpt.get();
            existingUser.setUsername(signUpRequest.getUsername());
            existingUser.setPassword(signUpRequest.getPassword());
            existingUser.setRole(UserRole.valueOf(signUpRequest.getRole()));
            existingUser.setEmail(signUpRequest.getEmail());

            // update other fields as necessary
            return userRepository.save(existingUser);
        } else {
            throw new UsernameNotFoundException("User not found with id " + id);
        }
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("User Not Found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByUsername(username)
                        .orElseThrow(()->new UsernameNotFoundException("UserNotFound"));
            }
        };
    }
}
