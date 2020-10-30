package com.myodan.board.service;


import com.myodan.board.domain.entity.Board;
import com.myodan.board.domain.repository.BoardRepository;
import com.myodan.board.dto.BoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public void savePost(BoardDto boardDto) {
        boardRepository.save(boardDto.toEntity());
    }

    @Transactional
    public List<BoardDto> getBoardDtoListByBoardList(List<Board> boardList) {
        List<BoardDto> boardDtoList = new ArrayList<>();
        for (Board board : boardList) {
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .author(board.getAuthor())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .createdDate(board.getCreatedDate())
                    .modifiedDate(board.getModifiedDate())
                    .build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    @Transactional
    public List<BoardDto> getBoardListByAuthor(String author) {
        List<Board> boardList = boardRepository.findByAuthorOrderByCreatedDateDesc(author);
        return getBoardDtoListByBoardList(boardList);
    }

    @Transactional
    public BoardDto getPost(Long id) {
        Board board = boardRepository.findById(id).get();

        return BoardDto.builder()
                .id(board.getId())
                .author(board.getAuthor())
                .title(board.getTitle())
                .content(board.getContent())
                .createdDate(board.getCreatedDate())
                .build();
    }

    public Page<BoardDto> getPostList(Pageable pageable) {
        Page<Board> boardPage = boardRepository.findAll(pageable);
        return boardPage.map(post -> BoardDto.builder()
                .id(post.getId())
                .author(post.getAuthor())
                .title(post.getTitle())
                .content(post.getContent())
                .createdDate(post.getCreatedDate())
                .modifiedDate(post.getModifiedDate())
                .build()
        );
    }

    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }
}