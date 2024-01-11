package com.example.missionPj.repo;

import com.example.missionPj.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    // 게시글 id기준으로 나중에 작성된 게시글을 최상단에 위치
    List<Article> findByBoardIdOrderByIdDesc(Long boardId);
}
