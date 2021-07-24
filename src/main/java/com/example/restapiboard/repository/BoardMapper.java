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

@Mapper
public interface BoardMapper {

    void save(BoardVo boardVo);
    List<BoardVo> findAll(Map map);
    BoardVo findOne(int id);
    void update(BoardVo boardVo);
    void deleteOne(int id);

    //게시글 총 개수 반환
    int count();

    //제목으로 게시글 검색
    List<BoardVo> search(String title);

    //좋아요 싫어요 반영
    void pressLike(LikeVo likeVo);

    //좋아요 싫어요 개수 반환
    //좋아요 테이블에서 해당 게시글의 좋아요 싫어요 정보를 다 가져와서 좋아요 싫어요 개수 파악하자
    List<LikeVo> countLike(int board_id);

    //특정 게시물에서 한 사람의 좋아요 싫어요 내역 가져오기기
    LikeVo getOneLikeHistory(HashMap map);

    //좋아요 지우기
    void deleteLike(HashMap map);

    //좋아요를 싫어요로 바꾸기
    void updateLikeToDislike(HashMap map);

    //싫어요를 좋아요로 바꾸기
    void updateDislikeToLike(HashMap map);
}

/*
@Select("SELECT * FROM BOARD")
@Results({
        @Result(property = "like", column = "LIKE_COUNT"),
        @Result(property = "dislike", column = "DISLIKE_COUNT")
})*/
