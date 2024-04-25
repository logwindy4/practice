package com.example.board.controller;

import com.example.board.dto.BoardDTO;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/")
    public String findAll(Model model){
        // DB에서 전체 게시글 데이터를 가져와서 list.html에 보여준다
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model ){ // 경로상의 값을 가져올 경우 @PathVariable 사용 데이터를 담아가야하니 model객체사용
    /*
    * 해당 게시글의 조회수를 하나 올리고
    * 게시글 데이터를 가져와서 detail.html에 출력
    * */
        boardService.updateHits(id);    // 서비스 클래스에 updateHitss 메서드 호출, (id)는 고유식별자
        BoardDTO boardDTO = boardService.findById(id);   // findById 메서드 호출 boardDTO 객체로 넣고
        model.addAttribute("board", boardDTO);  // boardDTO를 board라는 파라메터로지정 model객체에 addAttribute로 추가
//        model : Spring MVC에서 Controller에서 View로 데이터를 전달할 때 사용하는 객체
//        addAttribute : Spring MVC에서 Model 객체에 데이터를 추가할 때 사용하는 메소드
//        boardDTO : 실제 전달하고자 하는 데이터가 담긴 객체의 BoardDTO
//        (자료형) (변수명) = (내용물) 여기서도 적용되는건가?????????
        return "detail";
    }
}
