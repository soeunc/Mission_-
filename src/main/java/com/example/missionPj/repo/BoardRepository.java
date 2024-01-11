package com.example.missionPj.repo;

import com.example.missionPj.entity.Article;
import com.example.missionPj.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
