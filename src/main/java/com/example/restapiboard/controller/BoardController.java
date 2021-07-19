package com.example.restapiboard.controller;

import com.example.restapiboard.dto.BoardDto;
import com.example.restapiboard.service.BoardService;
import com.example.restapiboard.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    //게시글 불러오기
    @GetMapping("/list")
    public ResponseEntity<List<BoardVo>> showAllBoard(){
        log.info("게시글 불러오기");
        List<BoardVo> boards = boardService.findBoards();
        return ResponseEntity
                .ok(boards);
    }

    //게시글 작성하기
    //작성하기일 경우에만 HTTP헤더의 Content-Location을 이용하여 만들어진 리소스 생성된 위치를 알려준다
    //https://sanghaklee.tistory.com/61
    @PostMapping("/post")
    public ResponseEntity<BoardVo> createBoard(@RequestBody BoardDto boardDto, HttpServletRequest request){
        log.info("게시글 작성하기");
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(boardService.createBoard(boardDto,request).getId())//여기서 게시글 id가 반환 안돼서 location이 끝까지 완성이 안됨
                .toUri();

        return ResponseEntity
                .created(location)
                .build();
    }

    //게시글 1개 조회
    @GetMapping("/list/{id}")
    public ResponseEntity<BoardVo> detail(@PathVariable("id") int id) {
        log.info("게시글 1개 조회");
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(boardService.findOne(id))
                .toUri();

        return ResponseEntity
                .created(location)
                .build();
//        BoardVo board = boardService.findOne(id);
//        return ResponseEntity
//                .ok(board);
    }

    //게시글 삭제
    @DeleteMapping("/list/{id}")
    public ResponseEntity<BoardVo> deleteBoard(@PathVariable("id") int id) {
        log.info("게시글 삭제");
        boardService.deleteOne(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
