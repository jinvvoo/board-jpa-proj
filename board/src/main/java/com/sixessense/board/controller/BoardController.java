package com.sixessense.board.controller;

import com.sixessense.board.dto.BoardDTO;
import com.sixessense.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")        // id값만 받아오면 됨.
    public String findById(@PathVariable Long id, Model model) { //경로상 값 가져올 땐 @사용함
                                                                // 데이터 담아가야 하니 model 객체 사용
    /*
        해당 게시글의 조회수를 하나 올리고
        게시글 데이터를 가져와서 detail.html에 출력
     */
        boardService.updateHits(id);        // 두번의 호출이 발생 (find)
        BoardDTO boardDTO = boardService.findById(id);  // 두번의 호출이 발생 (힛)
        model.addAttribute("board", boardDTO);
        return "detail";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardUpdate", boardDTO);
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
        BoardDTO board = boardService.update(boardDTO);
        model.addAttribute("board", board);
        return "detail";
//        return "redirect:/board/" + boardDTO.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        boardService.delete(id);
        return "redirect:/board/";
    }

}
