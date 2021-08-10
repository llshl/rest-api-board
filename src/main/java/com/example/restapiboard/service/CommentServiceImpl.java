package com.example.restapiboard.service;

import com.example.restapiboard.dto.CommentDto;
import com.example.restapiboard.repository.BoardMapper;
import com.example.restapiboard.repository.CommentMapper;
import com.example.restapiboard.repository.LikeMapper;
import com.example.restapiboard.repository.MemberMapper;
import com.example.restapiboard.vo.CommentVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentMapper commentMapper;
    private final MemberMapper memberMapper;
    private final LikeMapper likeMapper;

    @Override
    public CommentVo createComment(CommentDto commentDto) {
        CommentVo commentVo = CommentVo.builder()
                .content(commentDto.getContent())
                .board_id(commentDto.getBoard_id())
                .member_nickname(memberMapper.findNicknameById(commentDto.getMember_id()))
                .date(LocalDateTime.now())
                .isUpdated(false)
                .build();
        commentMapper.save(commentVo);
        return commentVo;
    }

    @Override
    public List<CommentVo> findComments(int boardId) {
        return commentMapper.findAll(boardId);
    }

    @Override
    public CommentVo updateComment(CommentDto commentDto) {
        CommentVo commentVo = CommentVo.builder()
                .content(commentDto.getContent())
                .comment_id(commentDto.getComment_id())
                .isUpdated(true)
                .build();
        commentMapper.update(commentVo);
        return commentVo;
    }

    @Override
    public void deleteOne(int id) {
        commentMapper.delete(id);
    }
}
