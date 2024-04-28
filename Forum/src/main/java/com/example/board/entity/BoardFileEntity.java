package com.example.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "board_file_table")
public class BoardFileEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFIleName;

    @ManyToOne(fetch = FetchType.LAZY)  // LAZY 필요할때만 가져와쓴다?, EAGER 그냥 다가져온다?, LAZY를 거의씀
    @JoinColumn(name = "board_id")  // 테이블에 생성되는 이름
    private BoardEntity boardEntity;    // 부모 Entity 타입으로 적어야 한다

    public static BoardFileEntity toBoardFileEntity(BoardEntity boardEntity, String originalFileName, String storedFIleName){
        BoardFileEntity boardFileEntity = new BoardFileEntity();
        boardFileEntity.setOriginalFileName(originalFileName);
        boardFileEntity.setStoredFIleName(storedFIleName);
        boardFileEntity.setBoardEntity(boardEntity);
        return boardFileEntity;
    }
}
