package com.example.board.controller;

import com.example.board.dto.UserDTO;
import com.example.board.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

import java.util.List;
import java.util.Objects;

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
            System.out.println("로그인 되었습니다");
            return "/main";
        } else {
            System.out.println("일치하지 않습니다");
            return "/login";
        }
    }

    @GetMapping("/detail")
    public String redirectToMain() {
        return "redirect:/main";
    }

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
        model.addAttribute("user", userDTO);
        System.out.println("수정내용 = " + userDTO);
        return "detail";
    }

    @GetMapping("/board/update")
    public String updateForm(HttpSession session, Model model){
        String myNo = (String)session.getAttribute("loginId"); // 값을 담을때는 getAttribute 가져 올때는 setAttribute
                    // 강제형변환 Object -> Long
        UserDTO userDTO = userService.updateForm(myNo);         // userDTO 가져와서
        model.addAttribute("updateUser", userDTO);  // updateUser 담고
        return "update";                                        // update로 이동
    }

    @PostMapping("/board/update")
    public String update(@ModelAttribute UserDTO userDTO){
        userService.update(userDTO);
        System.out.println("업데이트?? = " + userDTO);
        return "redirect:/board/" + userDTO.getNo();    // 내정보를 수정후 완료된 상세페이지 띄워줌
    }

    @GetMapping("/board/delete/{no}")
    public String deleteById(@PathVariable Long no){
        userService.deleteById(no);
        System.out.println("삭제된:no = " + no);
        return "redirect:/board/list";
    }

    @GetMapping("/board/logout")
    public String logout(HttpSession session){
        session.invalidate();
        if(session != null){
            System.out.println("로그아웃");
        }
        return "index";
    }

//    @ResponseBody를 붙이면 관리자 콘솔에 입력값이 보여지게된다
    @PostMapping("/board/userId-check")
    public @ResponseBody String userIdCheck(@RequestParam("userId") String userId){
        System.out.println("userId = " + userId);
        String checkResult = userService.userIdCheck(userId);
        System.out.println("checkResult = " + checkResult);
        if(checkResult != null){
            return "ok";
        }else {
            return "no";
        }
    }
    @PostMapping("/board/email-check")
    public @ResponseBody String userEmailCheck(@RequestParam("email")String email){
        String checkResult = userService.userEmailCheck(email);
        System.out.println("checkResult = " + checkResult);
        if(checkResult != null){
            return "ok";
        }else {
            return "no";
        }

    }
}
