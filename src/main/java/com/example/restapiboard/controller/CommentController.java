package com.example.restapiboard.controller;

import com.example.restapiboard.dto.BoardDto;
import com.example.restapiboard.dto.CommentDto;
import com.example.restapiboard.service.CommentService;
import com.example.restapiboard.vo.BoardVo;
import com.example.restapiboard.vo.CommentVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    //ajax
    //해당 게시물의 댓글들 불러오기
    @GetMapping("/comment/{id}")
    public ResponseEntity<List<CommentVo>> showAllComments(@PathVariable("id") int boardId){
        log.info(boardId+"번 게시글의 댓글 불러오기");
        List<CommentVo> comments = commentService.findComments(boardId);
        return ResponseEntity
                .ok(comments);
    }

    //ajax
    //댓글 작성하기
    //작성하기일 경우에만 HTTP헤더의 Content-Location을 이용하여 만들어진 리소스 생성된 위치를 알려준다
    //https://sanghaklee.tistory.com/61
    @PostMapping("/comment/post")
    public ResponseEntity<CommentVo> createComment(@RequestBody CommentDto commentDto){
        log.info(commentDto.getBoard_id()+"번 게시물에 댓글 작성하기");
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(commentService.createComment(commentDto))//여기서 게시글 id가 반환 안돼서 location이 끝까지 완성이 안됨
                .toUri();
//
//        return ResponseEntity
//                .created(location)
//                .build();
//        CommentVo commentVo = commentService.createComment(commentDto);
        return ResponseEntity
                .created(location)
                .build();
    }

    //댓글 수정
    @PutMapping("/comment/{id}")
    public ResponseEntity<CommentVo> updateCommnet(@RequestBody CommentDto commentDto, @PathVariable("id") int id) {
        log.info(id+"번 게시글 수정");
        commentDto.setComment_id(id);
        CommentVo commentVo = commentService.updateComment(commentDto);
        return ResponseEntity
                .ok(commentVo);
    }

    //ajax
    //자바스크립트에서 이거 실행 후 페이지 리로딩 하자
    //게시글 삭제
    @DeleteMapping("/comment/{id}")
    public ResponseEntity<CommentVo> deleteComment(@PathVariable("id") int commentId) {
        log.info(commentId+"번 댓글 삭제");
        commentService.deleteOne(commentId);
        return ResponseEntity
                .noContent()
                .build();
    }
}
