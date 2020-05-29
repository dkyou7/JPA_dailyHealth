package com.spo.controller;

import com.spo.domain.Board;
import com.spo.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class BoardController {
    private BoardService boardService;

    @GetMapping("/board/new")
    public String createForm(Model model){
        model.addAttribute("boardForm",new BoardForm());
        return "board/createBoardForm";
    }

    @PostMapping("/board/new")
    public String create(@Valid BoardForm form, BindingResult result) {
        if(result.hasErrors()){
            return "users/createUserForm";
        }
        Board board = form.toEntity();
        boardService.savePost(board);

        return "redirect:/";
    }

    @GetMapping(value = "/boards")
    public String list(Model model){
        List<Board> boards = boardService.findPosts();
        model.addAttribute("boards",boards);
        return "board/boardList";
    }

    @GetMapping(value = "/board/{boardId}")
    public String detailForm(@PathVariable("boardId") Long id, Model model){
        Board findBoard = boardService.findOne(id);
        model.addAttribute("findBoard",findBoard);
        return "board/boardDetail";
    }

    @GetMapping(value = "/board/edit/{id}")
    public String updateItemForm(@PathVariable("id") Long id, Model model){
        Board findBoard = boardService.findOne(id);

        BoardForm form = BoardForm.builder()
                .id(findBoard.getId())
                .writer(findBoard.getWriter())
                .title(findBoard.getTitle())
                .content(findBoard.getContent())
                .build();
        model.addAttribute("form",form);
        return "board/updateBoardForm";
    }

    @PutMapping(value = "/board/edit/{id}")
    public String update(@Valid BoardForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "board/updateBoardForm";
        }
        Board board = form.toEntity();
        boardService.updatePost(board);

        return "redirect:/";
    }

    @DeleteMapping(value = "/board/{id}")
    public String delete(@PathVariable("boardId") Long id) {
        boardService.deletePost(id);

        return "redirect:/";
    }
}
