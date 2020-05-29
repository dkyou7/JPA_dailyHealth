package com.spo.controller;

import com.spo.domain.Board;
import com.spo.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
}
