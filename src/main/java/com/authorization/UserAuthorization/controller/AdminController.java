package com.authorization.UserAuthorization.controller;

import com.authorization.UserAuthorization.dao.request.SignUpRequest;
import com.authorization.UserAuthorization.entity.User;
import com.authorization.UserAuthorization.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    UserService userService;
    @GetMapping("/users/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable int id) {

        return new ResponseEntity<Optional<User>>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateUserDetails(@PathVariable int id, @RequestBody SignUpRequest signUpRequest) {
        try {
            User updatedUser = userService.updateUserDetails(id, signUpRequest);
            return ResponseEntity.ok(updatedUser);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }



}
