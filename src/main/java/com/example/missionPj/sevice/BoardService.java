package com.example.missionPj.sevice;

import com.example.missionPj.entity.Board;
import com.example.missionPj.repo.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public List<Board> readAll() {
       return boardRepository.findAll();
    }

    public Board readOne(Long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        return optionalBoard.orElse(null);
    }

}
