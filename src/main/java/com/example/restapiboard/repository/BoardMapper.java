package com.example.restapiboard.repository;

import com.example.restapiboard.dto.BoardDto;
import com.example.restapiboard.vo.BoardVo;
import com.example.restapiboard.vo.LikeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface BoardMapper {

    void save(BoardVo boardVo);
    List<BoardVo> findAll(Map map);
    Optional<BoardVo> findOne(int id);
    void update(BoardVo boardVo);
    void deleteOne(int id);

    //게시글 총 개수 반환
    int count();

    //제목으로 게시글 검색
    List<BoardVo> search(String title);
}

/*
@Select("SELECT * FROM BOARD")
@Results({
        @Result(property = "like", column = "LIKE_COUNT"),
        @Result(property = "dislike", column = "DISLIKE_COUNT")
})*/
