package com.authorization.UserAuthorization.controller;


import com.authorization.UserAuthorization.dao.request.SignInRequest;
import com.authorization.UserAuthorization.dao.request.SignUpRequest;
import com.authorization.UserAuthorization.dao.response.AuthorizationResponse;
import com.authorization.UserAuthorization.entity.User;
import com.authorization.UserAuthorization.service.AuthenticationService;
import com.authorization.UserAuthorization.service.UserActivityService;
import com.authorization.UserAuthorization.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    UserService userService;

    @Autowired
    UserActivityService activityService;

    @PostMapping("/signin")
    public ResponseEntity<Object> signIn(@RequestBody SignInRequest request) {

        try {

            AuthorizationResponse response = authenticationService.signIn(request);

            User user = userService.findByUsername(request.getUsername());
            activityService.recordLogin(user.getId());

            return ResponseEntity.ok(response);



        }catch (UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username or Password"+e.getMessage());
        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during sign-in"+e.getMessage());

        }

    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@RequestBody SignUpRequest request) {
        User user = authenticationService.signUp(request);
        return ResponseEntity.ok(user);
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {

        String header =request.getAuthType();
        System.out.println(header);

        return ResponseEntity.ok("logging out");

    }



    @GetMapping("/logout-success")
    public ResponseEntity<String> logoutsuccess(){
        return ResponseEntity.ok("Successful logout");
    }

}
