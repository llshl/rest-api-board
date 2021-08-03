package com.example.restapiboard.security;

import com.example.restapiboard.vo.MemberVo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

//MemberServiceImpl에서 사용됨
public class MemberDetailsImpl implements UserDetails {

    private final MemberVo memberVo;

    public MemberDetailsImpl(MemberVo memberVo) {
        this.memberVo = memberVo;
    }

    public MemberVo getMemberVo() {
        return memberVo;
    }

    public int getMemberId() {
        return memberVo.getMember_id();
    }

    @Override
    public String getPassword() {
        return memberVo.getLogin_password();
    }

    @Override
    public String getUsername() {
        return memberVo.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }
}