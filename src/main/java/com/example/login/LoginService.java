package com.example.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public LoginEntity CreateUser(LoginEntity loginEntity) {
        return loginRepository.save(loginEntity);
    }

    public LoginEntity checkLogin(String username, String password) {
        return loginRepository.findByUsernameAndPassword(username, password);
    }

}
