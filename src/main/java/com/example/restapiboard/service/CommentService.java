package com.example.restapiboard.service;

import com.example.restapiboard.dto.BoardDto;
import com.example.restapiboard.dto.CommentDto;
import com.example.restapiboard.dto.request.CreateCommentRequest;
import com.example.restapiboard.vo.BoardVo;
import com.example.restapiboard.vo.CommentVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CommentService {

    CommentVo createComment(CreateCommentRequest createCommentRequest);
    List<CommentVo> findComments(int id);
    CommentVo updateComment(CommentDto commentDto);
    void deleteOne(int id);
}