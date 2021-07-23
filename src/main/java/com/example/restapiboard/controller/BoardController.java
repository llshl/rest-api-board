package com.example.restapiboard.controller;

import com.example.restapiboard.config.Pagination;
import com.example.restapiboard.dto.BoardDto;
import com.example.restapiboard.dto.BoardListDto;
import com.example.restapiboard.service.BoardService;
import com.example.restapiboard.vo.BoardVo;
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
public class BoardController {

    // 한 페이지에 출력할 게시물 갯수
    private final int POST_NUMBER_PER_PAGE = 10;
    private final BoardService boardService;

    //게시글 불러오기
    @GetMapping("/list")
    public ResponseEntity<BoardListDto> showAllBoard(@RequestParam(value = "page", defaultValue = "1") int page){
        log.info("게시글 불러오기");
        int count = boardService.countAllBoard();   //게시글 총 개수
        int displayPost = (page - 1) * POST_NUMBER_PER_PAGE;    //해당 페이지에 출력할 첫번째 게시글 인덱스

        Pagination pagination = new Pagination();
        BoardListDto boardListDto = pagination.listPagination(page,count);  //게시글(boardVo)를 제외한 페이징 정보 갖고있다.
        boardListDto.setBoardVos(boardService.findBoards(displayPost,POST_NUMBER_PER_PAGE));     //boardVo 세팅

        return ResponseEntity
                .ok(boardListDto);
    }

    //게시글 작성하기
    //작성하기일 경우에만 HTTP헤더의 Content-Location을 이용하여 만들어진 리소스 생성된 위치를 알려준다
    //https://sanghaklee.tistory.com/61
    @PostMapping("/post")
    public ResponseEntity<BoardVo> createBoard(@RequestBody BoardDto boardDto, HttpServletRequest request){
        log.info("게시글 작성하기");
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(boardService.createBoard(boardDto,request).getBoard_id())//여기서 게시글 id가 반환 안돼서 location이 끝까지 완성이 안됨
                .toUri();

        return ResponseEntity
                .created(location)
                .build();
    }

    //게시글 1개 내용보기
    @GetMapping("/list/{id}")
    public ResponseEntity<BoardVo> detailBoard(@PathVariable("id") int id) {
        log.info(id+"번 게시글 1개 조회");
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(boardService.findOne(id))
//                .toUri();
//
//        return ResponseEntity
//                .created(location)
//                .build();
        BoardVo board = boardService.findOne(id);
        return ResponseEntity
                .ok(board);
    }

    //게시글 수정
    @PutMapping("/list/{id}")
    public ResponseEntity<BoardVo> updateBoard(@RequestBody BoardDto boardDto, @PathVariable("id") int id) {
        log.info(id+"번 게시글 수정");
        boardDto.setBoard_id(id);
        BoardVo board = boardService.updateOne(boardDto);
        return ResponseEntity
                .ok(board);
    }

    //게시글 삭제
    @DeleteMapping("/list/{id}")
    public ResponseEntity<BoardVo> deleteBoard(@PathVariable("id") int id) {
        log.info(id+"번 게시글 삭제");
        boardService.deleteOne(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    //게시글 검색
    @GetMapping("/list/search")
    public ResponseEntity<BoardListDto> searchBoard(@RequestParam(value = "page", defaultValue = "1") int page,
                                                     @RequestParam("title") String title) {
        log.info("게시글 검색 제목: "+title);
        List<BoardVo> boards = boardService.searchOne(title);
        int count = boards.size();
        int displayPost = (page - 1) * POST_NUMBER_PER_PAGE;

        Pagination pagination = new Pagination();
        BoardListDto boardListDto = pagination.listPagination(page,count);  //게시글(boardVo)를 제외한 페이징 정보 갖고있다.
        boardListDto.setBoardVos(boardService.findBoards(displayPost,POST_NUMBER_PER_PAGE));     //boardVo 세팅
        return ResponseEntity
                .ok(boardListDto);
    }


    //게시글 좋아요 누르기
    @PostMapping("/list/like/{id}")
    public ResponseEntity<BoardListDto> pressLike(@PathVariable("id") int id){
        log.info(id+"번 게시물 좋아요 처리");
        boardService.addLike(id);

        //헤테오스 적용

        return ResponseEntity
                .noContent()
                .build();
    }

    //게시글 싫어요 누르기 =>이거 좋아요 누르기랑 통일할수있을까? LikeType이 LIKE냐 DISLIKE냐의 차이만 있다.
    @PostMapping("/list/dislike/{id}")
    public ResponseEntity<BoardListDto> pressDislike(@PathVariable("id") int id){
        log.info(id+"번 게시물 싫어요 처리");
        boardService.addDislike(id);

        //헤테오스 적용


        return ResponseEntity
                .noContent()
                .build();
    }
}
