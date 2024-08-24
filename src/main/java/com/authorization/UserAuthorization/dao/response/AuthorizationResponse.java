package com.authorization.UserAuthorization.dao.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizationResponse {

    String username;
    String role;
    String email;
    String token;
    int id;


}
