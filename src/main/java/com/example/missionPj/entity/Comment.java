package com.example.missionPj.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String commentPassword;
    @ManyToOne
    private Article article;

    public boolean correctPassword(String inputPassword) {
        return this.commentPassword.equals(inputPassword);
    }
}
