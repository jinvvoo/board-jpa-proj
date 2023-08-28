package com.sixessense.board.service;


import com.sixessense.board.dto.BoardDTO;
import com.sixessense.board.entity.BoardEntity;
import com.sixessense.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    public List<BoardDTO> findAll() {
        // repository에서 가져올 땐 거의 대부분 entity로 가져온다.
        // list 형태 entity가 넘어오게 된 것.
        // Entity로 넘어온 이 객체를 우리는 BoardDTO 객체로 옮겨담아서 다시 controller로 return해줘야한다.
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        // return할 객체  선언   // 위에 있는 데이터(boardEntityList)를 아래(boardDTOList)로 담는다.
        List<BoardDTO> boardDTOList = new ArrayList<>();

        // entity객체를 dto객체로 담아야 하는 시점 -> BoardDTO로 넘어가서 작성


        for (BoardEntity boardEntity : boardEntityList) {   // 반목문으로 접근하는 entity 객체를 DTO로 변환을 하고
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity)); // 변환된 객체를 list에다가 받는다
        }

        return boardDTOList;    // for문 끝나면 list로 받는다.
    }

    @Transactional      // Jpa 메소드가 아닌 추가된 메소드인 경우 @ 작성 필수.
    public void updateHits(Long id) {
        boardRepository.updateHits(id);   //레포에 힛 호출

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
    // repo에 findById
    // optional 객체로 넘어왔기에 if문으로 get써서 BoardDTO로 변환, 리턴주기
    // 없으면 null 발생.


    public BoardDTO update(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);     // entity 변환작업
        boardRepository.save(boardEntity);
        return findById(boardDTO.getId());
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
