package com.forum.controllers;

import com.forum.dtos.users.UserRegisterDto;
import com.forum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController extends BaseController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
    public ModelAndView user(@PathVariable("username") String username, ModelAndView modelAndView) {
        modelAndView.addObject("username", username);
        return super.view("views/users/profile", "User " + username, modelAndView);
    }

    @GetMapping("/register")
    public ModelAndView register(@ModelAttribute UserRegisterDto userRegisterDto) {
        return super.view("views/users/register", "Register");
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@Valid @ModelAttribute UserRegisterDto userRegisterDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return super.view("views/users/register", "Register");
        }

        this.userService.createUser(userRegisterDto);
        return super.redirect("/login");
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return super.view("views/users/login", "Login");
    }
}
