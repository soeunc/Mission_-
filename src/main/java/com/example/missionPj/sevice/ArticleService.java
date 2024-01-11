package com.example.missionPj.sevice;

import com.example.missionPj.entity.Article;
import com.example.missionPj.repo.ArticleRepository;
import com.example.missionPj.repo.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;

    public void create(
            String articleTitle,
            String content,
            String password,
            Long boardId
    ) {
        Article article = new Article();
        article.setTitle(articleTitle);
        article.setContent(content);
        article.setPassword(password);
        article.setBoard(boardRepository.findById(boardId).orElse(null));
        articleRepository.save(article);
    }

    public List<Article> readAll() {
        return articleRepository.findAll();
    }

    public Article readOne(Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        return optionalArticle.orElse(null);
    }

    public boolean update(
            Long id,
            String articleTitle,
            String content,
            String password,
            Long boardId
    ) {
        Article articleEdit = articleRepository.findById(id).orElse(null);
        // 찾은 게시글이 null이 아니고, 입력된 비밀번호가 맞는 비밀번호면 데이터 수정
        if (articleEdit != null && articleEdit.correctPassword(password)) {
            articleEdit.setTitle(articleTitle);
            articleEdit.setContent(content);
            articleEdit.setBoard(boardRepository.findById(boardId).orElse(null));
            articleRepository.save(articleEdit);
            return true;
        }
        return false;
    }

    public boolean delete(Long id, String password) {
        Article articleDelete = articleRepository.findById(id).orElse(null);

        if (articleDelete != null && articleDelete.correctPassword(password)) {
            articleRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    // 해당 게시판에 게시글을 최신순으로 읽어온다.
    public List<Article> articleByBoard(Long boardId) {
        return articleRepository.findByBoardIdOrderByIdDesc(boardId);
    }



}
