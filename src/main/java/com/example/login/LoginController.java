package com.example.login;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/register")
    ResponseEntity<LoginEntity> createUser(@RequestBody LoginEntity loginEntity){
        return new ResponseEntity<>(loginService.CreateUser(loginEntity), HttpStatus.CREATED) ;
    }
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginEntity loginEntity) {

    LoginEntity user = loginService.checkLogin(
            loginEntity.getUsername(),
            loginEntity.getPassword()
    );

    if (user != null) {
        String token = loginService.generateToken(user.getUsername());

        return ResponseEntity.ok(Map.of(
                "username", user.getUsername(),
                "role", user.getRole(),
                "token", token   
        ));
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Invalid credentials"));
    }
}

}

