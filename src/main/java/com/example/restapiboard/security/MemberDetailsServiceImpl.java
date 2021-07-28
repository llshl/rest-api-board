package com.example.restapiboard.security;

import com.example.restapiboard.repository.MemberMapper;
import com.example.restapiboard.security.MemberDetailsImpl;
import com.example.restapiboard.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberDetailsServiceImpl implements UserDetailsService{

    private final MemberMapper memberMapper;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername 호출");
        log.info("현재 사용자: "+username);
        MemberVo memberVo = memberMapper.findByLoginId(username)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find " + username));    //인증실패
        return new MemberDetailsImpl(memberVo); //인증 성공
    }
}