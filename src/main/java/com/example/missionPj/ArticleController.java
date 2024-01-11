package com.example.missionPj;

import com.example.missionPj.sevice.ArticleService;
import com.example.missionPj.sevice.BoardService;
import com.example.missionPj.sevice.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("article")
public class ArticleController {
    private final ArticleService articleService;
    private final BoardService boardService;
    private final CommentService commentService;


    @GetMapping("create-v")
    public String creatView(Model model) {
        model.addAttribute("boards", boardService.readAll());
        return "article/create";
    }

    @PostMapping("create")
    public String articleCreate(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("password") String password,
            @RequestParam("board-id") Long boardId
    ) {
        articleService.create(title, content, password, boardId);
        return String.format("redirect:/boards/%d/article", boardId);
    }

    @GetMapping
    public String articlesOnAllBoard(Model model) {
        model.addAttribute("articles", articleService.readAll());
        return "article/home";
    }

    @GetMapping("{id}")
    public String articleReadOne(@PathVariable("id") Long articleId, Model model) {
        model.addAttribute("article", articleService.readOne(articleId));
        // 댓글 목록 불러오기
        model.addAttribute("commentList", commentService.commentByArticle(articleId));
        return "article/read";
    }

    @GetMapping("{id}/update-v")
    public String updateView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("article", articleService.readOne(id));
        model.addAttribute("boards", boardService.readAll());
        return "article/update";
    }

    @PostMapping("{id}/update")
    public String articleUpdate(
            @PathVariable("id") Long id,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("password") String password,
            @RequestParam("board-id") Long boardId
    ) {
        if (articleService.update(id, title, content, password, boardId)) {
            return "redirect:/boards";
        } else {
            // 비밀번호가 틀리면 다시 작성할 수 있는 수정폼으로 돌아감
            return String.format("redirect:/article/%d/update-v", id);
        }
    }

    @PostMapping("{id}/delete")
    public String articleDelete(@PathVariable("id") Long id,
                                @RequestParam("password") String password
                                ) {
        if (articleService.delete(id, password)) {
            return "redirect:/boards";
        } else {
            return String.format("redirect:/article/%d", id);
        }
    }
}
