package com.oneteam.joyread.dao;

import com.oneteam.joyread.entity.Booklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BooklistDAO extends JpaRepository<Booklist, Integer> {
    //获取推荐图书
    @Query(value = "select * from Booklist order by time desc LIMIT 2", nativeQuery = true)
    List<Booklist> getRecommendedBooklists();

    //获取书单详情
    Booklist getBooklistById(int id);
}
