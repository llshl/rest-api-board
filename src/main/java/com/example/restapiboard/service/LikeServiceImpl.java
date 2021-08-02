package com.example.restapiboard.service;

import com.example.restapiboard.repository.LikeMapper;
import com.example.restapiboard.vo.LikeVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService{

    private final LikeMapper likeMapper;

    @Override
    public void addLike(int id) {
        //id로 들어온 게시글번호를 통해 현재 게시물에 달린 좋아요들 목록을 가져오고 그 중에서 세션id를 통해 현재 사용자의 좋아요 내역을 조회
        //가져와졌다면 이미 눌린 상태라는 것이고 안가져와졌다면 처음 누르는 상태이므로 바로 반영하면됨
        //이미 눌린 상태였다면 좋아요가 눌렸었는지 싫어요가 눌렸었는지 확인 후 좋아요라면 토글을 통한 취소이니 _LIKE테이블에서 delete
        //싫어요라면 좋아요의 true를 false로 update
        HashMap<String, Integer> map = new HashMap<>();
        map.put("parent_id", id);   //현재 게시물에서
        map.put("session_id",3);    //내가 쓴 댓글    //@@@@@@@@@@@@@@@@이거 3 대신 세션에서 id 가져와서 넣어주자 @@@@@@@@@@@@@@@@@@@
        LikeVo oneLikeHistory = likeMapper.getOneLikeHistory(map);
        if(oneLikeHistory != null){
            //좋아요 내역이 있다면 이전에 누른적이 있다.
            if(oneLikeHistory.isLike_type()){
                //이전에 누른게 좋아요였다면 좋아요 취소(_LIKE테이블에서 삭제)
                likeMapper.deleteLike(map);
                return;
            }
            else{
                //이전에 누른게 싫어요 였다면 싫어요를 좋아요로 바꾸기
                likeMapper.updateDislikeToLike(map);
                return;
            }
        }
        //좋아요 싫어요를 처음 누르는 것이면 여기
        LikeVo likeVo = LikeVo.builder()
                .parent_type(true)  //true면 게시글
                .like_type(true)    //true면 좋아요
                .parent_id(id)
                .member_id(3)   //세션에서 사용자 id 넣어주자
                .build();
        likeMapper.pressLike(likeVo);
        return;
    }

    @Override
    public void addDislike(int id) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("parent_id", id);   //현재 게시물에서
        map.put("session_id",3);    //내가 쓴 댓글    //@@@@@@@@@@@@@@@@이거 3 대신 세션에서 id 가져와서 넣어주자 @@@@@@@@@@@@@@@@@@@
        LikeVo oneLikeHistory = likeMapper.getOneLikeHistory(map);
        if(oneLikeHistory != null){
            //싫어요 내역이 있다면 이전에 누른적이 있다.
            if(oneLikeHistory.isLike_type()){
                //이전에 누른게 좋아요였다면 싫어요로 바꾸기
                likeMapper.updateLikeToDislike(map);
                return;
            }
            else{
                //이전에 누른게 싫어요였다면 싫어요 취소(_LIKE 테이블에서 삭제)
                likeMapper.deleteLike(map);
                return;
            }
        }
        LikeVo likeVo = LikeVo.builder()
                .parent_type(true)
                .like_type(false)
                .parent_id(id)
                .member_id(3)   //세션에서 사용자 id 넣어주자
                .build();
        likeMapper.pressLike(likeVo);
    }
    //like랑 dislike랑 코드가 똑같은데 리팩토링 하자
    //두개가 다른 이유가 like랑 dislike랑 구별하는 유일한 방법이 url 리퀘스트를 다르게 하는것이어서 분리해놓았다.
}
