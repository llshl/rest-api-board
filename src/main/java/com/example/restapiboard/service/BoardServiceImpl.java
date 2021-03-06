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
        //????????? ????????? ?????? ??? ?????????id??? ????????? ?????? ???????????? ????????? ????????? ???????????? dto??? ????????? ??????
        //BoardVo2 boardVo = boardMapper.findOneByJoin(id)
        BoardVo boardVo = boardMapper.findOne(id)
                .orElseThrow(() -> new BoardNotFoundException(String.format("ID[%d] isn't exist", id)));
        List<LikeVo> likeVos = likeMapper.countLike(id);   //????????? ???????????? ????????? ????????? ??? ??? ??????????????? ????????? ???????????? ?????? Vo??? ????????? ??????
        int like_count = 0;
        int dislike_count = 0;
        boolean isPressedLikeOrDislike = false; //false??? ???????????????
        for(LikeVo likeVo : likeVos){
            if(likeVo.isLike_type()) like_count++;
            else dislike_count++;
            if(likeVo.getMember_id() == 3){     //@@@@@@@@@@@@@@@@@ ?????? 3 ?????? ????????? ?????? id??? ???????????? @@@@@@@@@@@@
                //????????? ??? if?????? 1?????? ????????????. ????????? 1?????? 1????????? ??????????????? ?????? _LIKE???????????? ??????????????? 1???????????? ????????????
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