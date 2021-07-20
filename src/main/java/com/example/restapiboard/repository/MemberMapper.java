package com.example.restapiboard.repository;

import com.example.restapiboard.dto.MemberDto;
import com.example.restapiboard.vo.CommentVo;
import com.example.restapiboard.vo.MemberVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    void save(MemberVo memberVo);
    MemberVo findOne(String nickname);
    void nicknameUpdate(MemberVo memberVo);
    void delete(int member_id);

    MemberVo login(String login_id);
}
