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

//    public static final String[] basicBoardTitle = {
//        "자유 게시판",
//        "개발 게시판",
//        "일상 게시판",
//        "사건사고 게시판"
//    };



    public List<Board> readAll() {
       return boardRepository.findAll();
    }

    public Board readOne(Long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        return optionalBoard.orElse(null);
    }

}
