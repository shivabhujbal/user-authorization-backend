package com.authorization.UserAuthorization.dao.request;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SignInRequest {

    private String username;
    private String password;

    public String getUsername() {

        return username;
    }
}
