package com.example.board.controller;


import com.example.board.dto.BoardDTO;
import com.example.board.dto.CommentDTO;
import com.example.board.service.BoardService;
import com.example.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller   // 내가 컨트롤러다 라고 지정하는 어노테이션
@RequiredArgsConstructor
@RequestMapping("/board")   // 최상위 경로 지정
public class BoardController {
    private final BoardService boardService;
    private final CommentService commentService;

    @GetMapping("/save")    // 하위 경로 지정
    public String saveForm() {
        return "save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) throws IOException {
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
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
    public String findById(@PathVariable Long id, Model model,    // 경로상의 값을 가져올 경우 @PathVariable 사용 데이터를 담아가야하니 model객체사용
                           @PageableDefault(page=1) Pageable pageable){
    /*
    * 해당 게시글의 조회수를 하나 올리고
    * 게시글 데이터를 가져와서 detail.html에 출력
    * */
        boardService.updateHits(id);    // 서비스 클래스에 updateHitss 메서드 호출, (id)는 고유식별자
        BoardDTO boardDTO = boardService.findById(id);   // findById 메서드 호출 boardDTO 객체로 넣고

        /* 댓글 목록 가져오기 */
        List<CommentDTO> commentDTOList = commentService.findAll(id);
        model.addAttribute("commentList", commentDTOList);
        model.addAttribute("board", boardDTO);  // boardDTO를 board라는 파라메터로지정 model객체에 addAttribute로 추가
//        model : Spring MVC에서 Controller에서 View로 데이터를 전달할 때 사용하는 객체
//        addAttribute : Spring MVC에서 Model 객체에 데이터를 추가할 때 사용하는 메소드
//        boardDTO : 실제 전달하고자 하는 데이터가 담긴 객체의 BoardDTO
//        (자료형) (변수명) = (내용물) 여기서도 적용되는건가?????????
        model.addAttribute("page", pageable.getPageNumber());
        return "detail";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model){
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardUpdate", boardDTO);
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model){
        BoardDTO board = boardService.update(boardDTO);
        model.addAttribute("board", board);
//        return "detail";
        return "redirect:/board/" + board.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){    // "/users/{id}"와 같은 URL에서 {id} 부분에 해당하는 값을 매개변수로 받아와야 할 때 @PathVariable을 사용
        boardService.delete(id);
        return "redirect:/board/";
    }

    @GetMapping("/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable,Model model){
        Page<BoardDTO> boardList = boardService.paging(pageable);
        int blockLimit = 5; // 보여지는 페이지 갯수
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) -1) * blockLimit +1;
        int endPage =((startPage + blockLimit -1) < boardList.getTotalPages()) ? startPage + blockLimit -1 : boardList.getTotalPages();

        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "paging";
    }
}