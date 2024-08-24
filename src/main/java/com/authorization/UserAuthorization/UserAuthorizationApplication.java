package com.authorization.UserAuthorization;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class UserAuthorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthorizationApplication.class, args);
	}

}
