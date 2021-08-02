package com.example.restapiboard.controller;

import com.example.restapiboard.dto.BoardDto;
import com.example.restapiboard.dto.CommentDto;
import com.example.restapiboard.service.CommentService;
import com.example.restapiboard.vo.BoardVo;
import com.example.restapiboard.vo.CommentVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;
    private WebMvcLinkBuilder getLinkAddress() {
        return linkTo(BoardController.class);
    }

    //해당 게시물의 댓글들 불러오기
    @GetMapping("/comment/{id}")
    public ResponseEntity showAllComments(@PathVariable("id") int boardId){
        log.info(boardId+"번 게시글의 댓글 불러오기");
        List<CommentVo> comments = commentService.findComments(boardId);
        List<EntityModel> collect = comments.stream()
                .map(commnet -> EntityModel.of(commnet,
                        getLinkAddress().slash(commnet.getComment_id()).withRel("update"),
                        getLinkAddress().slash(commnet.getComment_id()).withRel("delete")))
                .collect(Collectors.toList());
        CollectionModel entityModel = CollectionModel.of(collect,
                getLinkAddress().withSelfRel());
        return ResponseEntity
                .ok(entityModel);
    }

    //댓글 작성하기
    @PostMapping("/comment")
    public ResponseEntity createComment(@RequestBody CommentDto commentDto){
        log.info(commentDto.getBoard_id()+"번 게시물에 댓글 작성하기");
        CommentVo comment = commentService.createComment(commentDto);
        URI createdURI = getLinkAddress().slash(comment.getComment_id()).toUri();
        EntityModel<CommentVo> entityModel = EntityModel.of(comment,
                getLinkAddress().slash(comment.getComment_id()).withSelfRel(),
                getLinkAddress().slash(comment.getComment_id()).withRel("delete"),
                getLinkAddress().slash(comment.getComment_id()).withRel("update"));

        return ResponseEntity
                .created(createdURI)
                .body(entityModel);
    }

    //댓글 수정
    @PutMapping("/comment/{id}")
    public ResponseEntity updateCommnet(@RequestBody CommentDto commentDto, @PathVariable("id") int id) {
        log.info(id+"번 게시글 수정");
        commentDto.setComment_id(id);
       commentService.updateComment(commentDto);
        EntityModel entityModel = EntityModel.of(id,
                getLinkAddress().slash(id).withSelfRel());
        return ResponseEntity
                .ok(entityModel);
    }

    //게시글 삭제
    @DeleteMapping("/comment/{id}")
    public ResponseEntity deleteComment(@PathVariable("id") int id) {
        log.info(id+"번 댓글 삭제");
        commentService.deleteOne(id);
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("deletedId", id);
        EntityModel entityModel = EntityModel.of(resultMap,
                getLinkAddress().slash(id).withSelfRel());
        return ResponseEntity
                .ok(entityModel);
    }
}
