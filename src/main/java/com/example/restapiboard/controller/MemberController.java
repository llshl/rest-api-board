package com.example.restapiboard.controller;

import com.example.restapiboard.dto.MemberDto;
import com.example.restapiboard.service.MemberService;
import com.example.restapiboard.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    //회원가입
    @PostMapping("/join")
    public ResponseEntity<MemberVo> createMember(@RequestBody MemberDto memberDto){
        log.info("회원 가입하기");
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(memberService.joinMember(memberDto))
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

    //회원검색
    //이거 검색쿼리로 만들자
    @GetMapping("/member/{id}")
    public ResponseEntity<MemberVo> findOne(@PathVariable("id") String nickname){
        log.info("회원 검색");
        MemberVo memberVo =  memberService.findMember(nickname);
        //헤테오스 적용
        return ResponseEntity
                .ok(memberVo);
    }

    //넥네임 변경
    //변경할 닉네임을 파라미터 받아서 서비스단에서 키(세션의 회원id)와 값(새로바꿀 닉네임)을 매퍼로 넘겨줌
    @PutMapping("/member/{newNickname}")
    public ResponseEntity<MemberVo> updateNickname(@PathVariable("newNickname") String newNickname, HttpServletRequest request){
        log.info("닉네임 변경");
        memberService.updateNickname(newNickname, request);
        //헤테오스 적용
        return ResponseEntity
                .noContent()
                .build();
    }

    //회원삭제
    //이거 id를 어케알지? => 세션에있는 id로 삭제, 어자피 자기자신 삭제니까
    //세션에 아무 값 없으면 널포인트익셉션 발생
    @DeleteMapping("/member/delete")
    public ResponseEntity<MemberVo> deleteMember(HttpServletRequest request) {
        log.info("회원 삭제");

        memberService.deleteMemebr(request);
        return ResponseEntity
                .noContent()
                .build();
    }

//    //별명 검색
//    @GetMapping("/member/{id}")
//    public ResponseEntity<CommentVo> findOneByNickname(@PathVariable("id") int memberId) {
//        log.info("회원 삭제");
//        memberService.deleteMemebr(memberId);
//        return ResponseEntity
//                .noContent()
//                .build();
//    }

    //별명 중복검사
    //중복검사 결과를 true false로 반환하고 싶은데 이거를 어떤 포맷으로 반환하면 좋을지?
//    @PostMapping("/member/{id}")
//    public ResponseEntity<CommentVo> deleteMember(@PathVariable("id") int memberId) {
//        log.info("회원 삭제");
//        memberService.deleteMemebr(memberId);
//        return ResponseEntity
//                .noContent()
//                .build();
//    }
}
