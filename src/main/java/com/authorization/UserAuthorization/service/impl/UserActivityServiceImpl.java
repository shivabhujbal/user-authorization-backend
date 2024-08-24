package com.authorization.UserAuthorization.service.impl;

import com.authorization.UserAuthorization.entity.User;
import com.authorization.UserAuthorization.entity.UserActivity;
import com.authorization.UserAuthorization.repository.UserActivityRepository;
import com.authorization.UserAuthorization.repository.UserRepository;
import com.authorization.UserAuthorization.service.UserActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserActivityServiceImpl implements UserActivityService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserActivityRepository userActivityRepository;
    @Override
    public void recordLogin(int userId) {

        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User Not Found"));
        user.setLastLoginTime(LocalDateTime.now());
        userRepository.save(user);

        UserActivity userActivity = new UserActivity();
        userActivity.setUserId(userId);
        userActivity.setLoginTime(LocalDateTime.now());
        userActivityRepository.save(userActivity);

    }

    @Override
    public void recordLogout(int userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setLastLogoutTime(LocalDateTime.now());
        userRepository.save(user);

        UserActivity userActivity = userActivityRepository.findTopByUserIdOrderByLoginTimeDesc(userId);

        if(userActivity !=null && userActivity.getLogoutTime()==null){
            userActivity.setLogoutTime(LocalDateTime.now());
            userActivityRepository.save(userActivity);
        }
    }
}
