package com.example.restapiboard.repository;

import com.example.restapiboard.dto.BoardDto;
import com.example.restapiboard.vo.BoardVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface BoardMapper {

    void save(BoardVo boardVo);
    List<BoardVo> findAll();
    BoardVo findOne(int id);
}