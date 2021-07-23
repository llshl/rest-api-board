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
    public BoardVo findOne(int id) {
        return boardMapper.findOne(id);
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
        LikeVo likeVo = LikeVo.builder()
                .parent_type(ParentType.BOARD)
                .like_type(LikeType.LIKE)
                .parent_id(id)
                .member_id(3)   //세션에서 사용자 id 넣어주자
                .build();
        boardMapper.pressLike(likeVo);
    }

    @Override
    public void addDislike(int id) {
        LikeVo likeVo = LikeVo.builder()
                .parent_type(ParentType.BOARD)
                .like_type(LikeType.DISLIKE)
                .parent_id(id)
                .member_id(3)   //세션에서 사용자 id 넣어주자
                .build();
        boardMapper.pressLike(likeVo);
    }
}