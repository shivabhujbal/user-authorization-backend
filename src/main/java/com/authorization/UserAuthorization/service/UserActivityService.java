package com.authorization.UserAuthorization.service;

public interface UserActivityService {

    void recordLogin(int userId);
    void recordLogout(int userId);
}
