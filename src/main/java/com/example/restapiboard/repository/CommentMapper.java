package com.example.restapiboard.repository;

import com.example.restapiboard.vo.BoardVo;
import com.example.restapiboard.vo.CommentVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CommentMapper {

    void save(CommentVo commentVo);
    List<CommentVo> findAll(int boardId);
    void update(CommentVo commentVo);
    void delete(int commentId);
}
