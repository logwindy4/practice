package com.example.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")       // 최상위 주소
public class BoardController {
    @GetMapping("/save")        // 해당 주소로 이동하는 메소드
    public String saveForm(){
        return "save";
    }


    @GetMapping("/photo")
    public String photoForm(){
        return "photo";
    }

    @GetMapping("/video")
    public String videoForm(){
        return "video";
    }

    @GetMapping("/signup")
    public String signUpForm(){
        return "signup";
    }

    @GetMapping("/login")
    public String logInForm(){
        return "login";
    }
}
