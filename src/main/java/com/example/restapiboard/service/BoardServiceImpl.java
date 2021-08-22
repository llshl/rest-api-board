package com.example.restapiboard.service;

import com.example.restapiboard.dto.BoardDto;
import com.example.restapiboard.dto.request.CreateBoardRequest;
import com.example.restapiboard.dto.request.UpdateBoardRequest;
import com.example.restapiboard.exception.BoardException.BoardNotFoundException;
import com.example.restapiboard.repository.BoardMapper;
import com.example.restapiboard.repository.LikeMapper;
import com.example.restapiboard.vo.BoardVo;
import com.example.restapiboard.vo.LikeVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;
    private final LikeMapper likeMapper;

    @Override
    public BoardVo createBoard(CreateBoardRequest createBoardRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        BoardVo boardVo = BoardVo.builder()
                .title(createBoardRequest.getTitle())
                .content(createBoardRequest.getContent())
                .author(auth.getName())
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
    public void updateOne(UpdateBoardRequest updateBoardRequest) {
        BoardVo boardVo = BoardVo.builder()
                .board_id(updateBoardRequest.getBoard_id())
                .title(updateBoardRequest.getTitle())
                .content(updateBoardRequest.getContent())
                .isUpdated(true)
                .build();
        boardMapper.update(boardVo);
    }

    @Override
    public BoardDto findOne(int id) {
        //게시물 테이블 조회 후 게시글id를 통해서 해당 게시글의 좋아요 싫어요 개수까지 dto로 합쳐서 반환
        //BoardVo2 boardVo = boardMapper.findOneByJoin(id)
        BoardVo boardVo = boardMapper.findOne(id)
                .orElseThrow(() -> new BoardNotFoundException(String.format("ID[%d] isn't exist", id)));
        List<LikeVo> likeVos = likeMapper.countLike(id);   //어자피 좋아요와 싫어요 개수를 둘 다 알아야해서 정수로 받은것이 아닌 Vo로 한번에 받음
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
                //.orElseThrow(() -> new BoardNotFoundException(String.format("TITLE[%s] isn't exist", title)));
    }
}