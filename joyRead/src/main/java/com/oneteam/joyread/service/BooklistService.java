package com.oneteam.joyread.service;

import com.oneteam.joyread.entity.Booklist;

import java.util.List;

public interface BooklistService {
    //获取推荐书单
    public List<Booklist> getRecommendedBooklist(int userId);

    //获取书单信息
    public Booklist getBooklistInfo(int id);

    //获取书单具体信息
    public List<String> getBooklistBook(int id);
}
