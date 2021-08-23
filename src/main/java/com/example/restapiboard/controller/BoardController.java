package com.example.restapiboard.controller;

import com.example.restapiboard.config.Pagination;
import com.example.restapiboard.dto.BoardDto;
import com.example.restapiboard.dto.BoardListDto;
import com.example.restapiboard.dto.request.CreateBoardRequest;
import com.example.restapiboard.dto.request.UpdateBoardRequest;
import com.example.restapiboard.security.MemberDetailsImpl;
import com.example.restapiboard.service.BoardService;
import com.example.restapiboard.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final int POST_NUMBER_PER_PAGE = 10;
    private final BoardService boardService;
    private WebMvcLinkBuilder getLinkAddress() {
        return linkTo(BoardController.class);
    }

    //게시글 불러오기
    @GetMapping(value = "/list")
    public ResponseEntity showAllBoard(@RequestParam(value = "page", defaultValue = "1") int page){
        log.info(page+"페이지 게시글 불러오기");
        int count = boardService.countAllBoard();   //게시글 총 개수
        int displayPost = (page - 1) * POST_NUMBER_PER_PAGE;    //해당 페이지에 출력할 첫번째 게시글 인덱스

        BoardListDto boardListDto = Pagination.listPagination(page,count);  //게시글(boardVo)를 제외한 페이징 정보 갖고있다.
        boardListDto.setBoardVos(boardService.findBoards(displayPost,POST_NUMBER_PER_PAGE));     //boardVo 세팅
        return ResponseEntity
                .ok(boardListDto);
    }

    //게시글 작성하기
    //https://sanghaklee.tistory.com/61
    @PostMapping("/list")
    public ResponseEntity createBoard(@RequestBody CreateBoardRequest createBoardRequest){
        log.info("게시글 작성하기");
        BoardVo board = boardService.createBoard(createBoardRequest);
        URI createdURI = getLinkAddress().slash(board.getBoard_id()).toUri();
        EntityModel<BoardVo> entityModel = EntityModel.of(board,
                getLinkAddress().slash(board.getBoard_id()).withSelfRel(),
                getLinkAddress().slash(board.getBoard_id()).withRel("get"),
                getLinkAddress().slash(board.getBoard_id()).withRel("delete"),
                getLinkAddress().slash(board.getBoard_id()).withRel("update"));

        return ResponseEntity.created(createdURI).body(entityModel);
    }

    //게시글 1개 내용보기
    //게시글 1개 반환이지만 BoardListDto로 해도 될것같다?
    @GetMapping("/list/{id}")
    public ResponseEntity detailBoard(@PathVariable("id") int id,
                                      @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        log.info(id+"번 게시글 조회");
        BoardDto boardDto = boardService.findOne(id);
        boardDto.setAuthor_nickname(memberDetails.getMemberVo().getNickname());
        EntityModel entityModel = EntityModel.of(boardDto,
                getLinkAddress().slash(boardDto.getBoard_id()).withSelfRel(),
                getLinkAddress().slash(boardDto.getBoard_id()).withRel("update"),
                getLinkAddress().slash(boardDto.getBoard_id()).withRel("delete"),
                getLinkAddress().withRel("list"));
        return ResponseEntity
                .ok(entityModel);
    }

    //게시글 수정
    @PutMapping("/list/{id}")
    public ResponseEntity updateBoard(@RequestBody UpdateBoardRequest updateBoardRequest, @PathVariable("id") int id) {
        log.info(id+"번 게시글 수정");
        updateBoardRequest.setBoard_id(id);
        boardService.updateOne(updateBoardRequest);
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("updatedId", id);
        EntityModel entityModel = EntityModel.of(resultMap,
                getLinkAddress().slash(id).withSelfRel());
        return ResponseEntity
                .ok(entityModel);
    }

    //게시글 삭제
    @DeleteMapping("/list/{id}")
    public ResponseEntity deleteBoard(@PathVariable("id") int id) {
        log.info(id+"번 게시글 삭제");
        boardService.deleteOne(id);
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("deletedId", id);
        EntityModel entityModel = EntityModel.of(resultMap,
                getLinkAddress().slash(id).withSelfRel());
        return ResponseEntity
                .ok(entityModel);
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
        BoardListDto boardListDto = pagination.listPagination(page,count);
        boardListDto.setBoardVos(boardService.findBoards(displayPost,POST_NUMBER_PER_PAGE));
        return ResponseEntity
                .ok(boardListDto);
    }
}
