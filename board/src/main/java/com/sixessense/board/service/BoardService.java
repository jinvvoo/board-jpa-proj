package com.sixessense.board.service;


import com.sixessense.board.dto.BoardDTO;
import com.sixessense.board.entity.BoardEntity;
import com.sixessense.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


// DTO -> Entity (Entity Class)
// Entity -> DTO (DTO Class)

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        // 이 메소드객체(BoardEntity.toSaveEntity(boardDTO);)를 실행한 결과를 엔티티 객체(boardEntity)로 받아올 수 있고
        // 결국 Entity를 save 메소드로 넘겨준다.
        boardRepository.save(boardEntity);
        // insert 쿼리가 나가게 된다.

    }
}
