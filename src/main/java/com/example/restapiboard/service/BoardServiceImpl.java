package com.example.restapiboard.service;

import com.example.restapiboard.config.MemberInformation;
import com.example.restapiboard.dto.BoardDto;
import com.example.restapiboard.dto.LikeType;
import com.example.restapiboard.dto.ParentType;
import com.example.restapiboard.repository.BoardMapper;
import com.example.restapiboard.vo.BoardVo;
import com.example.restapiboard.vo.LikeVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;
    private final MemberInformation memberInformation;

    @Override
    public BoardVo createBoard(BoardDto boardDto, HttpServletRequest request) {
        BoardVo boardVo = BoardVo.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                //.author(userInformation.getUserName(request))
                .author("tester")
                .isUpdated(false)
                .date(LocalDateTime.now())
                .build();
        boardMapper.save(boardVo);
        return boardVo;
    }

    @Override
    public List<BoardVo> findBoards(int displayPost, int postNum) {
        Map<String, Integer> map = new HashMap<>();
        map.put("displayPost", displayPost);
        map.put("postNum", postNum);
        return boardMapper.findAll(map);
    }

    @Override
    public BoardVo updateOne(BoardDto boardDto) {
        BoardVo boardVo = BoardVo.builder()
                .board_id(boardDto.getBoard_id())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .isUpdated(true)
                .build();
        boardMapper.update(boardVo);
        return boardVo;
    }

    @Override
    public BoardDto findOne(int id) {
        //게시물 테이블 조회 후 게시글id를 통해서 해당 게시글의 좋아요 싫어요 개수까지 dto로 합쳐서 반환
        BoardVo boardVo = boardMapper.findOne(id);
        List<LikeVo> likeVos = boardMapper.countLike(id);   //어자피 좋아요와 싫어요 개수를 둘 다 알아야해서 정수로 받은것이 아닌 Vo로 한번에 받음
        int like_count = 0;
        int dislike_count = 0;
        boolean isPressedLikeOrDislike = false; //false는 안눌린상태
        for(LikeVo likeVo : likeVos){
            if(likeVo.isLike_type()) like_count++;
            else dislike_count++;
            if(likeVo.getMember_id() == 3){     //@@@@@@@@@@@@@@@@@ 여기 3 대신 사용자 세션 id가 들어간다 @@@@@@@@@@@@
                //어자피 이 if문은 1번만 참이된다. 사용자 1명이 1번밖에 못누른다는 것은 _LIKE테이블에 사용자들이 1명씩밖에 없다는것
                isPressedLikeOrDislike = true;
            }
        }
        BoardDto boardDto = BoardDto.builder()
                .board_id(id)
                .title(boardVo.getTitle())
                .author(boardVo.getAuthor())
                .content(boardVo.getContent())
                .date(boardVo.getDate())
                .isUpdated(boardVo.isUpdated())
                .like_count(like_count)
                .dislike_count(dislike_count)
                .isPressedLikeOrDislike(isPressedLikeOrDislike)
                .build();
        return boardDto;
    }

    @Override
    public void deleteOne(int id) {
        boardMapper.deleteOne(id);
    }

    @Override
    public int countAllBoard() {
        return boardMapper.count();
    }

    @Override
    public List<BoardVo> searchOne(String title) {
        return boardMapper.search(title);
    }

    @Override
    public void addLike(int id) {
        //id로 들어온 게시글번호를 통해 현재 게시물에 달린 좋아요들 목록을 가져오고 그 중에서 세션id를 통해 현재 사용자의 좋아요 내역을 조회
        //가져와졌다면 이미 눌린 상태라는 것이고 안가져와졌다면 처음 누르는 상태이므로 바로 반영하면됨
        //이미 눌린 상태였다면 좋아요가 눌렸었는지 싫어요가 눌렸었는지 확인 후 좋아요라면 토글을 통한 취소이니 _LIKE테이블에서 delete
        //싫어요라면 좋아요의 true를 false로 update
        HashMap<String, Integer> map = new HashMap<>();
        map.put("parent_id", id);   //현재 게시물에서
        map.put("session_id",3);    //내가 쓴 댓글    //@@@@@@@@@@@@@@@@이거 3 대신 세션에서 id 가져와서 넣어주자 @@@@@@@@@@@@@@@@@@@
        LikeVo oneLikeHistory = boardMapper.getOneLikeHistory(map);
        if(oneLikeHistory != null){
            //좋아요 내역이 있다면 이전에 누른적이 있다.
            if(oneLikeHistory.isLike_type()){
                //이전에 누른게 좋아요였다면 좋아요 취소(_LIKE테이블에서 삭제)
                boardMapper.deleteLike(map);
                return;
            }
            else{
                //이전에 누른게 싫어요 였다면 싫어요를 좋아요로 바꾸기
                boardMapper.updateDislikeToLike(map);
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
        boardMapper.pressLike(likeVo);
        return;
    }

    @Override
    public void addDislike(int id) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("parent_id", id);   //현재 게시물에서
        map.put("session_id",3);    //내가 쓴 댓글    //@@@@@@@@@@@@@@@@이거 3 대신 세션에서 id 가져와서 넣어주자 @@@@@@@@@@@@@@@@@@@
        LikeVo oneLikeHistory = boardMapper.getOneLikeHistory(map);
        if(oneLikeHistory != null){
            //싫어요 내역이 있다면 이전에 누른적이 있다.
            if(oneLikeHistory.isLike_type()){
                //이전에 누른게 좋아요였다면 싫어요로 바꾸기
                boardMapper.updateLikeToDislike(map);
                return;
            }
            else{
                //이전에 누른게 싫어요였다면 싫어요 취소(_LIKE 테이블에서 삭제)
                boardMapper.deleteLike(map);
                return;
            }
        }
        LikeVo likeVo = LikeVo.builder()
                .parent_type(true)
                .like_type(false)
                .parent_id(id)
                .member_id(3)   //세션에서 사용자 id 넣어주자
                .build();
        boardMapper.pressLike(likeVo);
    }
    //like랑 dislike랑 코드가 똑같은데 리팩토링 하자
    //두개가 다른 이유가 like랑 dislike랑 구별하는 유일한 방법이 url 리퀘스트를 다르게 하는것이어서 분리해놓았다.

}