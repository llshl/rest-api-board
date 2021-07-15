package com.example.restapiboard.service;

import com.example.restapiboard.config.UserInformation;
import com.example.restapiboard.dto.BoardDto;
import com.example.restapiboard.repository.BoardMapper;
import com.example.restapiboard.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;
    private final UserInformation userInformation;

    @Override
    public BoardVo createBoard(BoardDto boardDto, HttpServletRequest request) {
        BoardVo boardVo = BoardVo.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                //.author(userInformation.getUserName(request))
                .author("tester")
                .like(0)
                .dislike(0)
                .date(LocalDateTime.now())
                .build();
        System.out.println(boardVo.getTitle());
        System.out.println(boardVo.getDate());
        System.out.println(boardVo.getContent());
        boardMapper.save(boardVo);
        return boardVo;
    }

    @Override
    public List<BoardVo> findBoards() {
        log.info("2");
        return boardMapper.findAll();
    }

    @Override
    public BoardVo findOneBoard(int id) {
        return boardMapper.findOne(id);
    }
}