package com.forum.areas.user.services;

import com.forum.dtos.users.UserRegisterDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void createUser(UserRegisterDto bindingModel);

    boolean isUsernameTaken(String username);

    boolean isEmailTaken(String email);
}