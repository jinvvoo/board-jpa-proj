package com.sixessense.board.controller;

import com.sixessense.board.dto.BoardDTO;
import com.sixessense.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) {
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "index";
    }

    @GetMapping("/")
    public String findAll(Model model) {  // DB에서 전체 게시글 데이터를 가져와서 list.html에 보여준다.
        // DB에서 목록을 가져와야 한다.
        // 모델객체를 가져와야 한다. -> findAll(Model model) 모델 객체 가져온다.

        // 게시글 목록이기 때문에, 한개가 아니라 여러개, List를 사용.
        List<BoardDTO> boardDTOList = boardService.findAll();
        // 가져온 데이터를 모델객체에 담아가야한다.
        model.addAttribute("boardList", boardDTOList);
        // 어디로? list로.
        return "list";

    }

}
