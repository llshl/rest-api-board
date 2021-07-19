package com.example.restapiboard.repository;

import com.example.restapiboard.dto.BoardDto;
import com.example.restapiboard.vo.BoardVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface BoardMapper {

    void save(BoardVo boardVo);
    List<BoardVo> findAll();
    BoardVo findOne(int id);
    void deleteOne(int id);
}

/*
@Select("SELECT * FROM BOARD")
@Results({
        @Result(property = "like", column = "LIKE_COUNT"),
        @Result(property = "dislike", column = "DISLIKE_COUNT")
})*/
