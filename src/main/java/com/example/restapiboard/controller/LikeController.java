package com.example.restapiboard.controller;

import com.example.restapiboard.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
    private WebMvcLinkBuilder getLinkAddress() {
        return linkTo(LikeController.class);
    }

    //게시글 좋아요 누르기
    @PostMapping("/like/{id}")
    public ResponseEntity pressLike(@PathVariable("id") int id){  //게시글번호가 들어온다
        log.info(id+"번 게시물 좋아요 처리");
        likeService.addLike(id);
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("likedId", id);
        EntityModel entityModel = EntityModel.of(resultMap,
                getLinkAddress().slash(id).withSelfRel(),
                getLinkAddress().withRel("list"));
        return ResponseEntity
                .ok(entityModel);
    }

    @PostMapping("/dislike/{id}")
    public ResponseEntity pressDislike(@PathVariable("id") int id){
        log.info(id+"번 게시물 싫어요 처리");
        likeService.addDislike(id);
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("dislikedId", id);
        EntityModel entityModel = EntityModel.of(resultMap,
                getLinkAddress().slash(id).withSelfRel(),
                getLinkAddress().withRel("list"));
        return ResponseEntity
                .ok(entityModel);
    }
}
