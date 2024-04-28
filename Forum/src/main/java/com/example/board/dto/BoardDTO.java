package com.example.board.dto;

import com.example.board.entity.BoardEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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

    private MultipartFile boardFile;    // save.html -> Controller 파일 담는 용도
    private String originalFileName;    // 원본 파일 이름
    private String storedFilName;       // 서버 저장용 파일 이름 (파일 이름이 같을시 서버 입장 에서는 내가 원하는 파일을 구분 할수 없다)
    private int fileAttached;           // 파일 첨부 여부(첨부 1, 미첨부 0)  boolean 값으로 해도 되지만 복잡, int 0,1로 구분 하는게 손쉽다

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
        if(boardEntity.getFileAttached() == 0){
            boardDTO.setFileAttached(boardEntity.getFileAttached());    // 0
        }else{
            boardDTO.setFileAttached(boardEntity.getFileAttached());    // 1
            // 파일 이름을 가져가야 함
            // orginalFileName, storedFileName : board_file_table(BoardFileEntity)
            // join
            // select * from board_table b, board_file_table bf where b.id=bf.board_id and where b.id=?
            boardDTO.setOriginalFileName(boardEntity.getBoardFileEntityList().get(0).getOriginalFileName());
            boardDTO.setStoredFilName(boardEntity.getBoardFileEntityList().get(0).getStoredFIleName());
        }

        return boardDTO;
    }

}
