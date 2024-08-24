package com.authorization.UserAuthorization.controller;

import com.authorization.UserAuthorization.entity.User;
import com.authorization.UserAuthorization.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {


    @Autowired
    UserService userService;
    @GetMapping("/users/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable int id) {

        return new ResponseEntity<Optional<User>>(userService.getUserById(id), HttpStatus.OK);
    }
}
