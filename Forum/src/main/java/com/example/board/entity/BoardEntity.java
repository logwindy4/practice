package com.example.board.entity;

import com.example.board.dto.BoardDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// DB의 테이블 역할을 하는 클래스
@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity {
    @Id     // pk 컬럼 지정. 필수
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(length = 20, nullable = false)  // 크기는 20, not null
    private String boardWriter;

    @Column // 크기 255, null 가능 (지정안할시)
    private String boardPass;

    @Column
    @NotBlank(message = "제목을 입력해 주세요")
    private String boardTitle;

    @Column(length = 500)
    @NotBlank(message = "내용을 입력해 주세요")
    private String boardContents;

    @Column
    private int boardHits;

    @Column
    private int fileAttached;   // 1 or 0
    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    // 어떤것과 메칭이 되느냐(BaseEntity에 boardEntity 이름과 같게 맞춰준다)
    // cascade = CascadeType.REMOVE, orphanRemoval = true 온딜리트 케스케이드 속성을 주는 부분
    // fetch = FetchType.LAZY 페치는 레이지로 지정
    private List<BoardFileEntity> boardFileEntityList = new ArrayList<>();
    // 게시글 하나에 BoardFileEntity가 여러개 올수 있도록 참조관계 설정(보드하나에 보드파일이 리스트형태로 가져와져야 한다, 리스트 타입으로 선언)

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE,orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    // DTO에 담긴 값들을 Entity 객체로 옮기는 작업
    // 입력된 값이 DTO에 저장되면 아래의 get~~ 내용들이 boardEntity 값으로 set
    public static BoardEntity toSaveEntity(BoardDTO boardDTO){  // DTO에 담긴 값을 Entity 객체로 옮기는 메소드
        BoardEntity boardEntity = new BoardEntity();            // 객체생성
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());  // boardDTO 객체의 boardWriter 변수의 값을 가져와서 boardEntity 객체의 boardWriter 변수에 설정
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(0);
        boardEntity.setFileAttached(0); // 파일 없음.(값이0)
        return boardEntity;
    }

// id값이 존재하는 상태로 엔티티객체가 넘어오면 jpa는 update를 하라고 대응 없다면 insert로 대응한다
    public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardDTO.getId());     // <<<<
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(boardDTO.getBoardHits());
        return boardEntity;
    }

    public static BoardEntity toSaveFileEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(0);
        boardEntity.setFileAttached(1); // 파일 있음.
        return boardEntity;
    }
}
