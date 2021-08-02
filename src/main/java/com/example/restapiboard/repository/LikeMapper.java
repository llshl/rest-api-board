package com.example.restapiboard.repository;

import com.example.restapiboard.vo.BoardVo;
import com.example.restapiboard.vo.LikeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface LikeMapper {

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
