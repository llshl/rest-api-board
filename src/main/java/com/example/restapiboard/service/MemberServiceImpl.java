package com.example.restapiboard.service;

import com.example.restapiboard.config.MemberInformation;
import com.example.restapiboard.config.SecurityUtil;
import com.example.restapiboard.dto.CommentDto;
import com.example.restapiboard.dto.MemberDto;
import com.example.restapiboard.exception.DuplicatedLoginxception;
import com.example.restapiboard.repository.CommentMapper;
import com.example.restapiboard.repository.MemberMapper;
import com.example.restapiboard.vo.CommentVo;
import com.example.restapiboard.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{

    private final MemberMapper memberMapper;
    private final MemberInformation memberInformation;

    @Override
    public MemberVo joinMember(MemberDto memberDto) {

        MemberVo findMemberWhoIsSameId = memberMapper.login(memberDto.getLogin_id());
        //회원가입 아이디 중복 확인
        if(findMemberWhoIsSameId != null) {
            log.info("회원가입 중복검사 실패");
            throw new DuplicatedLoginxception(String.format("ID[%s] is already occupied", memberDto.getLogin_id()));
        }
        log.info("회원가입 중복검사 통과");
        //sha256
        SecurityUtil sha = new SecurityUtil();
        String encryptedPassword = sha.encryptSHA256(memberDto.getLogin_password());

        MemberVo memberVo = MemberVo.builder()
                .login_id(memberDto.getLogin_id())
                .login_password(encryptedPassword)
                .email(memberDto.getEmail())
                .name(memberDto.getName())
                .nickname(memberDto.getNickname())
                .build();
        memberMapper.save(memberVo);
        return memberVo;
    }

    @Override
    public MemberVo findMember(String nickname) {
        return memberMapper.findOne(nickname);
    }

    @Override
    public void updateNickname(String newNickname, HttpServletRequest request) {
        MemberVo memberVo = MemberVo.builder()
                .member_id(memberInformation.getMemberId(request))
                .nickname(newNickname)
                .build();
        memberMapper.nicknameUpdate(memberVo);
    }

    @Override
    public void deleteMemebr(HttpServletRequest request) {
        int member_id = memberInformation.getMemberId(request);
        memberMapper.delete(member_id);
    }

    @Override
    public String login(MemberDto memberDto, HttpSession session) {
        MemberVo findMember = memberMapper.login(memberDto.getLogin_id());
        SecurityUtil sha = new SecurityUtil();
        String encryptedPassword = sha.encryptSHA256(memberDto.getLogin_password());

        if(findMember.getLogin_id().equals(encryptedPassword)){
            if(findMember.getLogin_password().equals(memberDto.getLogin_password())){
                //로그인 성공
                session.setAttribute("LOGIN_SESSION",findMember.getMember_id());
                return "success";
            }
            else{
                return "wrong-password";
            }
        }
        return "no-exist";
    }
}
