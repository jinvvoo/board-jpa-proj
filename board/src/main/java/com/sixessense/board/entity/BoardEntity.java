package com.sixessense.board.entity;

import com.sixessense.board.dto.BoardDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment (mysql 기준)
    private Long id;

    @Column(length = 20, nullable = false)      // 글자수 제한, null일 수 없다고 제한.
    private String boardWriter;

    @Column // 크기 255, null가능으로 기본세팅
    private String boardPass;

    @Column
    private String boardTitle;

    @Column(length = 500)
    private String boardContents;

    @Column
    private int boardHits;

    public static BoardEntity toSaveEntity(BoardDTO boardDTO) {
        //BoardDTO에 담겨온(save html에서 입력한 값) 것을 Entity의 boardWriter로 set한다는 뜻.
        // DTO에 담긴 값을 Entity에 옮겨 담는다.
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(0);    // 조회수 기본 값은 0이니까
        return boardEntity;
    }

}