package com.example.board.dto;

import com.example.board.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

// DTO(Data Transfer Object)
@Getter
@Setter
@ToString
@NoArgsConstructor  // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class BoardDTO {
    private Long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdatedTime;

    public BoardDTO(Long id, String boardWriter, String boardTitle, int boardHits, LocalDateTime boardCreatedTime) {
        this.id = id;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardHits = boardHits;
        this.boardCreatedTime = boardCreatedTime;
    }

    // Entity에 담긴 값들을 DTO 객체로 옮기는 작업
    // 입력된 값이 Entity에 저장되면 아래의 get~~ 내용들이 boardDTO 값으로 set
    public static BoardDTO toBoardDTO(BoardEntity boardEntity) {    // Entity값을 DTO로 변경 하는 메소드
        BoardDTO boardDTO = new BoardDTO();                         // BoardDTO 객체 생성
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardPass(boardEntity.getBoardPass());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
        boardDTO.setBoardUpdatedTime(boardEntity.getUpdatedTime());
        return boardDTO;
    }
}
