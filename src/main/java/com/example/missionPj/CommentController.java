package com.example.missionPj;

import com.example.missionPj.sevice.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("article/{id}/comment")
public class CommentController {
    private final CommentService commentService;

    // 댓글 달기 - 다시 게시글 단 곳으로 돌아온다. 댓글 작성한걸 가지고
    // 입력받은 값 넣어주고,
    // 게시글을 가져오고, 가져온 게시글에 댓글을 넣어준다.
    @PostMapping
    public String comment(@PathVariable("id") Long articleId,
                          @RequestParam("content") String content,
                          @RequestParam("commentPW") String commentPassword
    ) {
        commentService.create(content, commentPassword, articleId);
        return String.format("redirect:/article/%d", articleId);
    }

    // 댓글 삭제 - 게시글id에서 원하는 댓글만 삭제
    @PostMapping("{commentId}/delete")
    public String commentDelete(@PathVariable("id") Long articleId,
                                @PathVariable("commentId") Long commentId,
                                @RequestParam("commentPW") String commentPW
    ) {
        commentService.delete(commentId, commentPW);
        return String.format("redirect:/article/%d", articleId);
    }
}
