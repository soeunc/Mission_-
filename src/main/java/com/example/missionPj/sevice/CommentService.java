package com.example.missionPj.sevice;

import com.example.missionPj.entity.Article;
import com.example.missionPj.entity.Comment;
import com.example.missionPj.repo.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleService articleService;

    public Comment create(
            String content,
            String commentPW,
            Long articleId
    ) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCommentPassword(commentPW);
        Article article = articleService.readOne(articleId);
        comment.setArticle(article);
        commentRepository.save(comment);
        return comment;
    }

    public void delete(Long commentId, String commentPW) {
        Comment commentDelete = commentRepository.findById(commentId).orElse(null);

        if (commentDelete != null && commentDelete.correctPassword(commentPW)) {
            commentRepository.deleteById(commentId);
        }
    }

    // 게시글에서 댓글 목록 보기
    public List<Comment> commentByArticle(Long articleId) {
        return commentRepository.findByArticleId(articleId);
    }

}
