package com.example.board.service;

import com.example.board.dto.BoardDTO;
import com.example.board.entity.BoardEntity;
import com.example.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// DTO -> Entity
// Entity -> DTO
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void post(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
        System.out.println("저장? = " + boardEntity.getId());
    }

    public List<BoardDTO> findAll() {   // 화면에 보여줄 게시물을 모두 찾아서 리스트로 만들어주는 메소드
        List<BoardEntity> boardEntityList = boardRepository.findAll();  // DB에서 모든 게시물을 가져오는 코드 boardEntityList에 저장
        List<BoardDTO> boardDTOList = new ArrayList<>();    // ArrayList<>()라는 변수생성하고 boardDTOList 이름짓고 List<BoardDTO>의 값을 넣는다
        for(BoardEntity boardEntity: boardEntityList){  // DB에서 모든 게시물을 가져온 게시물을 하나씩 가져와서 boardEntity변수에 넣고 작업
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity)); // BoardDTO클래스안에 toBoardDTO메소드를 사용 BoardEntity객체를 BoardDTO 객체로 변환
                                                                // 변환된 객체를 boardDTOList 에 추가
            System.out.println("보드엔터티 = " + boardDTOList);
        }
        return boardDTOList;
    }
// jpa에서 제공중인 메서드들은 조회수증가나 특수한목적을 가진 커리들은 잘 되질 않아서 별도의 메서드를 정의해준다
    @Transactional  // 별도의 추가된 메서드를 쓸 경우에는 트렌젝셔널 어노테이션은 붙인다(안붙이면 에러?)
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }
    @Transactional
    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        } else {
            return null;
        }
    }
}
