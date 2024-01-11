package com.example.missionPj.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String password;

    @ManyToOne
    private Board board;
    @OneToMany(mappedBy = "article")
    private List<Comment> articleComment;

    // 게시글을 작성할 때 사용자가 입력한 비밀번호가 해당 게시글 엔터티의 비밀번호로 저장되고,
    // 이후에 게시글을 수정할 때 비밀번호 확인에 사용되는 메서드

    // 입력된 비밀번호가 게시글 작성 때 입력한 값과 일치하는가
    public boolean correctPassword(String articlePassword) {
        return this.password.equals(articlePassword);
    }


}
