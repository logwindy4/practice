package com.example.board.controller;

import com.example.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignUpController {
    private UserService userService;
    @Autowired
    public void signup(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signUpForm(){
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@RequestParam String id, @RequestParam String password, @RequestParam String email){
        userService.createUser(id, password, email);
        return "redirect:/login";
    }
}
