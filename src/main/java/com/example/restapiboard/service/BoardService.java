package com.example.restapiboard.service;

import com.example.restapiboard.dto.BoardDto;
import com.example.restapiboard.vo.BoardVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface BoardService {

    BoardVo createBoard(BoardDto boardDto, HttpServletRequest request);
    List<BoardVo> findBoards();
    BoardVo findOne(int id);
}