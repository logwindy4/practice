package com.example.board.controller;

import com.example.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @GetMapping("/login")
    public String loginFrom(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String id, @RequestParam String password){
        return "redirect:/index";
    }
}
