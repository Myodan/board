package com.myodan.board.domain.repository;

import com.myodan.board.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    public List<Board> findByAuthorOrderByCreatedDateDesc(String auther);
    public List<Board> findAllByOrderByCreatedDateDesc();
}
