package com.example.board.controller;

import com.example.board.dto.UserDTO;
import com.example.board.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/login")
    public String loginFrom(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserDTO userDTO, HttpSession session) {
        UserDTO loginResult = userService.login(userDTO);
        if (loginResult != null) {
            session.setAttribute("loginId", loginResult.getUserId());
            System.out.println("loginResult = " + loginResult.getUserId());
            System.out.println("userDTO = " + userDTO.getUserId());
            System.out.println("아이디가 있습니다");
            return "/main";
        } else {
            System.out.println("일치하지 않습니다");
            return "/login";
        }
    }

//    @GetMapping("/list")
//    public String listForm(){
//        return "list";
//    }
    @GetMapping("/board/list")
    public String findAll(Model model) {
        List<UserDTO> userDTOList = userService.findAll();
        // 어떠한 html로 가져갈 데이터가 있다면 model사용
        model.addAttribute("userList", userDTOList);
        System.out.println("userDTOList = " + userDTOList);
        return "list";
    }

    @GetMapping("/board/{no}")
    public String findById(@PathVariable Long no, Model model) {
        UserDTO userDTO = userService.findByNo(no);
        model.addAttribute("board", userDTO);
        return "detail";
    }
}
