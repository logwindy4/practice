package com.example.board.controller;

import com.example.board.dto.UserDTO;
import com.example.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
@RequiredArgsConstructor
public class SignUpController {
    // 생성자 주입
    private final UserService userService;

    @GetMapping("/signup")
    public String signUpForm(){
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute UserDTO userDTO){
        // @ModelAttribute = Http에 입력된값을 DTO로 저장해서 DTO에 저장된 값을 불러와서 사용할때
        // @RequestParam = HTTP 요청에서 파라미터 값을 추출할 때 사용, http에 아이디를 입력해서 보내면 String 값으로 id에 저장
        // @RequestParam String id, @RequestParam String password, @RequestParam String email, @RequestParam String name
        System.out.println("SignUpController.signUp 요청이 오니");
        System.out.println("userDTO = " + userDTO);
        userService.createUser(userDTO);
        return "redirect:/login";
    }
}
