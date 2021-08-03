package com.example.restapiboard.controller;

import com.example.restapiboard.security.MemberDetailsImpl;
import com.example.restapiboard.service.MemberService;
import com.example.restapiboard.vo.CommentVo;
import com.example.restapiboard.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private WebMvcLinkBuilder getLinkAddress() {
        return linkTo(BoardController.class);
    }

    //회원검색
    @GetMapping("/member/search")
    public ResponseEntity findOne(@RequestParam("nick") String nickname){
        log.info("회원 검색");
        MemberVo memberVo =  memberService.findMember(nickname);
        EntityModel entityModel = EntityModel.of(memberVo,
                getLinkAddress().slash(memberVo.getMember_id()).withSelfRel(),
                getLinkAddress().withRel("list"));
        return ResponseEntity
                .ok(entityModel);
    }

    //넥네임 변경
    @PutMapping("/member/{newNickname}")
    public ResponseEntity updateNickname(@PathVariable("newNickname") String newNickname,
                                                   @AuthenticationPrincipal MemberDetailsImpl memberDetails){
        log.info("닉네임 변경");
        memberService.updateNickname(newNickname,memberDetails.getMemberVo().getMember_id());   //이거도 서비스에서 id 넣어주자
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("updated Nickname", newNickname);
        EntityModel entityModel = EntityModel.of(resultMap,
                getLinkAddress().slash(newNickname).withSelfRel());
        return ResponseEntity
                .ok(entityModel);
    }

    //회원삭제
    @DeleteMapping("/member/{id}")
    public ResponseEntity deleteMember(@PathVariable("id") int id) {
        log.info("회원 삭제");
        memberService.deleteMemebr(id);
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("dislikedId", id);
        EntityModel entityModel = EntityModel.of(resultMap,
                getLinkAddress().slash(id).withSelfRel(),
                getLinkAddress().withRel("list"));
        return ResponseEntity
                .ok(entityModel);
    }

    //별명 중복검사
    //중복검사 결과를 true false로 반환하고 싶은데 이거를 어떤 포맷으로 반환하면 좋을지?
//    @PostMapping("/member/{id}")
//    public ResponseEntity<CommentVo> checkNickname(@PathVariable("id") int memberId) {
//        log.info("회원 삭제");
//        memberService.deleteMemebr(memberId);
//        return ResponseEntity
//                .noContent()
//                .build();
//    }
}
