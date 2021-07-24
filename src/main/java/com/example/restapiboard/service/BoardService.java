package com.example.restapiboard.service;

import com.example.restapiboard.dto.BoardDto;
import com.example.restapiboard.vo.BoardVo;
import com.example.restapiboard.vo.LikeVo;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BoardService {

    BoardVo createBoard(BoardDto boardDto, HttpServletRequest request);
    List<BoardVo> findBoards(int displayPost, int postNum);
    BoardVo updateOne(BoardDto boardDto);
    BoardDto findOne(int id);
    void deleteOne(int id);

    int countAllBoard();
    List<BoardVo> searchOne(String title);
    void addLike(int id);
    void addDislike(int id);

    //HashMap<String, Integer> countLike(int board_id);
}