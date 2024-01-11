package com.example.missionPj;

import com.example.missionPj.sevice.ArticleService;
import com.example.missionPj.sevice.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("boards")
public class BoardController {
    private final BoardService boardService;
    private final ArticleService articleService;

    // 게시판 전체 조회
    @GetMapping
    public String boardReadAll(Model model) {
        model.addAttribute("boards", boardService.readAll());
        return "board/home";
    }

    // 게시판 들어가면 하나의 게시글만 보이는 링크
    @GetMapping("{boardId}/article")
    public String boardReadOne(@PathVariable("boardId") Long boardId, Model model) {
        // 게시판 제목 조회를 위해 넣음
        model.addAttribute("board", boardService.readOne(boardId));
        // 해당 게시판의 게시글 조회
        model.addAttribute("articleList", articleService.articleByBoard(boardId));
        return "board/read";
    }
}
