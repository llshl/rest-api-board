package com.example.restapiboard.service;

public interface LikeService {

    void addLike(int id, int member_id);
    void addDislike(int id, int member_id);

}