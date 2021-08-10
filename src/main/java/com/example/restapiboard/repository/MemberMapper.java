package com.example.restapiboard.repository;

import com.example.restapiboard.dto.MemberDto;
import com.example.restapiboard.security.MemberDetailsImpl;
import com.example.restapiboard.vo.CommentVo;
import com.example.restapiboard.vo.MemberVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    void save(MemberVo memberVo);
    void updateMember(MemberVo memberVo);
    void nicknameUpdate(MemberVo memberVo);
    void delete(int member_id);
    int findIdByLoginId(String loginId);

    Optional<MemberVo> findByNickname(String nickname);
    Optional<MemberVo> findByLoginId(String login_id);
    Optional<MemberVo> findByKakaoId(Long kakaoId);
    Optional<MemberVo> findByEmail(String email);
    String findNicknameById(int member_id);
}
