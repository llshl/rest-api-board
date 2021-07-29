package com.example.restapiboard.service;

import com.example.restapiboard.config.MemberInformation;
import com.example.restapiboard.config.SecurityUtil;
import com.example.restapiboard.dto.CommentDto;
import com.example.restapiboard.dto.MemberDto;
import com.example.restapiboard.exception.DuplicatedLoginxception;
import com.example.restapiboard.repository.CommentMapper;
import com.example.restapiboard.repository.MemberMapper;
import com.example.restapiboard.security.MemberDetailsImpl;
import com.example.restapiboard.security.kakao.KakaoOAuth2;
import com.example.restapiboard.security.kakao.KakaoUserInfo;
import com.example.restapiboard.vo.CommentVo;
import com.example.restapiboard.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;
    private final KakaoOAuth2 kakaoOAuth2;
    private final AuthenticationManager authenticationManager;
    private final MemberInformation memberInformation;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Override
    public MemberVo joinMember(MemberDto memberDto) {

        MemberVo findMemberWhoIsSameId = memberMapper.findByLoginId(memberDto.getLogin_id())
                .orElse(null);
        //회원가입 아이디 중복 확인
        if(findMemberWhoIsSameId != null) {
            log.info("회원가입 중복검사 실패");
            throw new DuplicatedLoginxception(String.format("ID[%s] is already occupied", memberDto.getLogin_id()));
        }
        log.info("회원가입 중복검사 통과");

        //spring security encrypt
        String encryptedPassword = passwordEncoder.encode(memberDto.getLogin_password());

        MemberVo memberVo = MemberVo.builder()
                .login_id(memberDto.getLogin_id())
                .login_password(encryptedPassword)
                .email(memberDto.getEmail())
                .name(memberDto.getName())
                .nickname(memberDto.getNickname())
                .kakao_id(null)
                .build();
        memberMapper.save(memberVo);
        return memberVo;
    }

    @Override
    public MemberVo findMember(String nickname) {
        return memberMapper.findByNickname(nickname).orElse(null);
    }

    @Override
    public void updateNickname(String newNickname, int memberId) {
        MemberVo memberVo = MemberVo.builder()
                .member_id(memberId)
                .nickname(newNickname)
                .build();
        memberMapper.nicknameUpdate(memberVo);
    }

    @Override
    public void deleteMemebr(int memberId) {
        memberMapper.delete(memberId);
    }

    public void kakaoLogin(String authorizedCode) {
        log.info("kakaoLogin Service 호출");
        // 카카오 OAuth2 를 통해 카카오 사용자 정보 조회
        KakaoUserInfo userInfo = kakaoOAuth2.getUserInfo(authorizedCode);
        System.out.println("userInfo.getNickname() = " + userInfo.getNickname());

        //카카오에서 받아온 사용자의 정보
        Long kakaoId = userInfo.getId();
        String nickname = userInfo.getNickname();
        String email = userInfo.getEmail();

        //카카오 로그인을 통해 이미 회원가입한 회원인지 확인하기 위해 카카오ID를 통해 검색
        MemberVo kakaoMember = memberMapper.findByKakaoId(kakaoId)
                .orElse(null);

        //중복된 사용자가 없다면(처음으로 카카오 로그인을 하는 경우 카카오에서 받은 정보를 통한 회원가입 진행)
        if(kakaoMember == null) {
            MemberVo sameEmailMember = memberMapper.findByEmail(email).orElse(null);
            if(sameEmailMember != null){
                //카카오로그인은 처음인데 이미 그냥 회원가입은 돼있는경우
                kakaoMember = sameEmailMember;
                kakaoMember.setKakao_id(kakaoId);    //이미 저장돼있는 회원 정보에 카카오 ID만 추가해서 다시 저장한다.
                memberMapper.updateMember(kakaoMember);     //기존 회원에 카카오 아이디만 추가해준다
            }
            else{
                //그냥 회원가입도, 카카오 회원가입도 안돼있는경우 새롭게 kakaoUser객체 만들어서 DB에 저장(회원가입)
                String userName = nickname;
                String password = kakaoId + ADMIN_TOKEN;
                String encodedPassword = passwordEncoder.encode(password);
                kakaoMember = MemberVo.builder()
                        .kakao_id(kakaoId)
                        .login_id(email)    //카카오 로그인의 경우에는 login id가 없기에 이메일을 넣어줌
                        .nickname(nickname)
                        .name(userName)
                        .email(email)
                        .login_password(encodedPassword)
                        .build();
                memberMapper.save(kakaoMember); //회원가입

                //카카오 로그인 후 바로 사용자의 정보를 담은 MemberDetailsImpl의 객체가 생성되는데
                //이때 데이터베이스상의 id는 가져와있지 않기때문에 추가적인 쿼리를 통해서 db상의 member_id를 가져와서
                //memberDetails만들때 setter로 넣어줌
                int idByLoginId = memberMapper.findIdByLoginId(email);
                kakaoMember.setMember_id(idByLoginId);
            }
        }

        // kakaoMember 객체에 로그인 할 정보를 담고있음
        // 로그인 처리
        // 스프링 시큐리티 통해 인증된 사용자로 등록
        MemberDetailsImpl memberDetails = new MemberDetailsImpl(kakaoMember);
        Authentication authentication = new UsernamePasswordAuthenticationToken(memberDetails, null, memberDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        System.out.println("현재 사용자 정보");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("auth.getName() = " + auth.getName());
    }
}
