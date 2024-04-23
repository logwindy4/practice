package com.example.board.controller;

import com.example.board.dto.BoardDTO;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller   // 내가 컨트롤러다 라고 지정하는 어노테이션
@RequiredArgsConstructor
@RequestMapping("/board")   // 최상위 경로 지정
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/post")    // 하위 경로 지정
    public String postForm() {
        return "post";
    }

    @PostMapping("/post")
    public String post(@ModelAttribute BoardDTO boardDTO){
        System.out.println("boardDTO = " + boardDTO);
        boardService.post(boardDTO);
        return "index";
    }
}
