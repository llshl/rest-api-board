package com.example.restapiboard.service;

import com.example.restapiboard.dto.BoardDto;
import com.example.restapiboard.vo.BoardVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface BoardService {

    BoardVo createBoard(BoardDto boardDto, HttpServletRequest request);
    List<BoardVo> findBoards(int displayPost, int postNum);
    BoardVo updateOne(BoardDto boardDto);
    BoardVo findOne(int id);
    void deleteOne(int id);

    int countAllBoard();
    List<BoardVo> searchOne(String title);
    void addLike(int id);
    void addDislike(int id);
}