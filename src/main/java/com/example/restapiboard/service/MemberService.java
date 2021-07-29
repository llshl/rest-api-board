package com.example.restapiboard.service;

import com.example.restapiboard.dto.CommentDto;
import com.example.restapiboard.dto.MemberDto;
import com.example.restapiboard.security.MemberDetailsImpl;
import com.example.restapiboard.vo.CommentVo;
import com.example.restapiboard.vo.MemberVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface MemberService {

    MemberVo joinMember(MemberDto memberDto);
    MemberVo findMember(String nickname);
    void updateNickname(String newNickname, int memberId);
    void deleteMemebr(int memberId);

    //String login(MemberDto memberDto, HttpSession session);
    void kakaoLogin(String authorizedCode);
}