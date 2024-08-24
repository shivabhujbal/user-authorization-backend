package com.authorization.UserAuthorization.service;

import com.authorization.UserAuthorization.dao.request.SignInRequest;
import com.authorization.UserAuthorization.dao.request.SignUpRequest;
import com.authorization.UserAuthorization.dao.response.AuthorizationResponse;
import com.authorization.UserAuthorization.entity.User;

public interface AuthenticationService {

    AuthorizationResponse signIn(SignInRequest request);
    User signUp(SignUpRequest request);
}
