package com.example.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

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
    public String login(@RequestBody LoginEntity loginEntity) {

        LoginEntity user = loginService.checkLogin(
                loginEntity.username,
                loginEntity.password
        );

        if (user != null) {
            return "hi " + user.getRole();
        } else {
            return "invalid";
        }
    }
}

